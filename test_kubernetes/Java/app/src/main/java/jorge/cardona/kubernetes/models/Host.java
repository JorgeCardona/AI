package jorge.cardona.kubernetes.models;

import lombok.Data;

@Data
public class Host {

    private String hostname;
    private int port;
}
