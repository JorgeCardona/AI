package jorge.cardona.concepts.controller;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jorge.cardona.concepts.entity.NatureEntity;
import jorge.cardona.concepts.repository.NatureInterfaceRepository;
import jorge.cardona.concepts.service.NatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@OpenAPIDefinition()
@RestController
@RequestMapping("/nature")
@CrossOrigin(origins = "http://localhost:3000", methods= {RequestMethod.GET, RequestMethod.POST})
public class NatureController {

    @Autowired
    NatureService natureService;

    @Autowired
    NatureInterfaceRepository natureInterfaceRepository;


//    @Operation(summary = "Nature Elements",
//            description = "Obtain Elements and Details about the nature"
//    )
//    @Tag(name = "Get All Registers from Nature")
//    @ApiResponses({    @ApiResponse(responseCode = "400", description = "Bad Request",
//            content = @Content(mediaType = MediaType.TEXT_HTML_VALUE,
//                    examples = { @ExampleObject(
//                            value ="Unauthorized")})),
//            @ApiResponse(responseCode = "401", description = "Unauthorized",
//                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
//                            examples = {
//
//                                    @ExampleObject(
//                                            name = "List of Elements of Nature",
//                                            value = "[\n" +
//                                                    "    {\n" +
//                                                    "        \"id\": \"2140224c-342e-44db-acbc-72162b381d69\",\n" +
//                                                    "        \"kingdom\": \"Plant\",\n" +
//                                                    "        \"description\": \"Trees, plants and other species of vegetation make up part of the Plantae kingdom - one of the oldest, and characterised by its immobile, multicellular and eukaryotic nature. These autotrophic things, whose cells contain cellulose and chlorophyll are essential for life on Earth since they release oxygen through photosynthesis. As regards their method of reproduction, this may be either sexual or asexual.\",\n" +
//                                                    "        \"createDateTime\": \"2021-05-26T23:24:24.618+00:00\",\n" +
//                                                    "        \"updateDateTime\": \"2021-05-26T23:24:24.618+00:00\"\n" +
//                                                    "    },\n" +
//                                                    "    {\n" +
//                                                    "        \"id\": \"084e860e-c8a4-4ea3-a9af-4f47a6dcd697\",\n" +
//                                                    "        \"kingdom\": \"Animal\",\n" +
//                                                    "        \"description\": \"The kingdom Animalia is the most evolved and is divided into two large groups - vertebrates and invertebrates. These animals are multi-celled, heterotrophic eukaryotes with aerobic respiration, sexual reproduction and the ability to move. This kingdom is one of the most diverse and comprises mammals, fish, birds, reptiles, amphibians, insects, molluscs and annelids, among others.\",\n" +
//                                                    "        \"createDateTime\": \"2021-05-26T23:24:24.650+00:00\",\n" +
//                                                    "        \"updateDateTime\": \"2021-05-26T23:24:24.650+00:00\"\n" +
//                                                    "    }\n" +
//                                                    "]",
//                                            summary = "List Element",
//                                            description = "some examples about of nature"
//                                    ),
//                                    @ExampleObject(
//                                            name = "One Element of Nature",
//                                            value = "    {\n" +
//                                                    "        \"id\": \"6b6eb2b2-1fbd-4337-a5db-3c93a37e5266\",\n" +
//                                                    "        \"kingdom\": \"Monera\",\n" +
//                                                    "        \"description\": \"This is the kingdom of microscopic living things and groups together the prokaryotes (archaea and bacteria). This group is present in all habitats and is made up of single-cell things with no defined nucleus. Most bacteria are aerobic and heterotrophic, while the archaea are usually anaerobic and their metabolism is chemosynthetic.\",\n" +
//                                                    "        \"createDateTime\": \"2021-05-26T23:24:24.660+00:00\",\n" +
//                                                    "        \"updateDateTime\": \"2021-05-26T23:24:24.660+00:00\"\n" +
//                                                    "    }",
//                                            summary = "Unique Element",
//                                            description = "One example about of nature"
//                                    ) }
//                    )
//            )})

//    @Parameters({
//            @Parameter(name = "QUERY", required = true, in = ParameterIn.QUERY, example = "1") ,
//            @Parameter(name = "COOKIE", required = true, in = ParameterIn.COOKIE, example = "SESSION_ID") ,
//            @Parameter(name = "PATH", required = true, in = ParameterIn.PATH, example = "vegetal") ,
//            @Parameter(name = "HEADER", required = true, in = ParameterIn.HEADER, example = "Authorization"),
//            @Parameter(name = "Authorization", required = true, in = ParameterIn.HEADER, example = "Corporation")
//    })
//    @Parameter(name = "QUERY", required = true, in = ParameterIn.QUERY, example = "1")
//    @Parameter(name = "COOKIE", required = true, in = ParameterIn.COOKIE, example = "SESSION_ID")
//    @Parameter(name = "PATH", required = true, in = ParameterIn.PATH, example = "vegetal")
//    @Parameter(name = "HEADER", required = true, in = ParameterIn.HEADER, example = "Authorization")
//    @Parameter(name = "Authorization", required = true, in = ParameterIn.HEADER, example = "Corporation")
//    @Schema(accessMode = Schema.AccessMode.AUTO)

    @Operation(summary = "Update an existing pet",
            tags = {"pets"},
            security = @SecurityRequirement(
                    name = "basicAuth",
                    scopes = "write:pets"),
            responses = {
                    @ApiResponse(
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = AnimalController.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
                    @ApiResponse(responseCode = "404", description = "Pet not found"),
                    @ApiResponse(responseCode = "405", description = "Validation exception") }
    )
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/getall")
    @ResponseBody
    public ResponseEntity natureJson(){

        return ResponseEntity.ok(natureInterfaceRepository.getNatureList());
    }

    public static final String AUTHORIZATION = "Authorization";

    @SecurityRequirement(name = "BasicAuth")
    @SecurityRequirement(name = "BearerAuth")
    @SecurityRequirement(name = "ApiKeyAuth")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/getallrepo")
    @ResponseBody
    public ResponseEntity natureJsonRepo(@Parameter(in = ParameterIn.HEADER, name = "Authorization")
                                              @RequestHeader(HttpHeaders.AUTHORIZATION) String creds){

        return ResponseEntity.ok(natureService.getNatureList());
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(path = "/save")
    public ResponseEntity save(@Valid @RequestBody NatureEntity natureEntity, Errors errors){

        if(errors.hasErrors()){

            return ResponseEntity.badRequest().body(errors.getAllErrors());
        }
        return new ResponseEntity(natureService.saveNature(natureEntity), HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(path = "/savelist")
    public ResponseEntity list(@RequestBody List<NatureEntity> natureEntity){

        return new ResponseEntity(natureService.saveListNature(natureEntity), HttpStatus.OK);
    }

    @Hidden
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/internal")
    public ResponseEntity test(){

        return new ResponseEntity("INTERNAL SERVICE", HttpStatus.OK);
    }
}