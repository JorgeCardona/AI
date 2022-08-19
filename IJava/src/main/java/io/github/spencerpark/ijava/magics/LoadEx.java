/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2018 Spencer Park
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package io.github.spencerpark.ijava.magics;

import io.github.spencerpark.jupyter.channels.JupyterSocket;
import io.github.spencerpark.jupyter.kernel.magic.common.Load;
import io.github.spencerpark.jupyter.kernel.magic.registry.LineMagic;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.logging.Level;

public class LoadEx extends Load {

    public LoadEx(List<String> fileExtensions, Executor exec) {
        super(fileExtensions, exec);
    }

    @LineMagic
    public void load(List<String> args) throws Exception {
        try {
            String src = args.get(0);
            if (src.startsWith("http")) {
                URL url = new URL(src);
                String fileName = url.getFile();
                int pos = fileName.lastIndexOf('/');
                if (pos > 0) {
                    fileName = fileName.substring(pos + 1);
                }
                Path tmp = Files.createTempFile("tmp", fileName);
                try (InputStream is = url.openStream()) {
                    Files.copy(is, tmp, StandardCopyOption.REPLACE_EXISTING);
                }
                args.set(0, tmp.toAbsolutePath().toString());
                super.load(args);
                Files.deleteIfExists(tmp);
            } else {
                super.load(args);
            }
        } catch (IOException e) {
            JupyterSocket.JUPYTER_LOGGER.log(Level.WARNING, "", e);
            throw e;
        }
    }
}
