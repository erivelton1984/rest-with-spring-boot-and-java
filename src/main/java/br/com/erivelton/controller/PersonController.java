package br.com.erivelton.controller;

import br.com.erivelton.data.vo.v1.PersonVO;
import br.com.erivelton.data.vo.v2.PersonVO2;
import br.com.erivelton.service.PersonServices;
import br.com.erivelton.util.MediaType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin
@RestController
@RequestMapping("/api/person/v1")
@Tag(name="People", description = "Endpoints for Managing People")
public class PersonController {

    @Autowired
    private PersonServices personServices;

    @GetMapping(produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(summary = "Finds all People",
            description = "Finds all people",
            tags = {"People"},
            responses = {
                @ApiResponse(description = "Success", responseCode = "200",
                        content = {@Content(

                                array = @ArraySchema(schema= @Schema(implementation = PersonVO.class))
                        )
                }
            ),
                @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    public List<PersonVO> findAll (){
        return personServices.findAll();
    }

    //@CrossOrigin(origins = "http://localhost:8080")
    @GetMapping(value = "/{id}",
            produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    public PersonVO findById (
            @PathVariable (value = "id") Long id) throws Exception{
        return personServices.findById(id);
    }

    @CrossOrigin(origins = {"http://localhost:8080", "https://erivelton.com.br"})
    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
            produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    public PersonVO create (@RequestBody PersonVO person) throws Exception{
        return personServices.create(person);
    }

    @PostMapping(value = "/v2",
            consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
            produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    public PersonVO2 createV2 (@RequestBody PersonVO2 person) throws Exception{
        return personServices.create(person);
    }

    @PutMapping(
            consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
            produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    public PersonVO update (@RequestBody PersonVO person) throws Exception{
        return personServices.update(person);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete (@PathVariable (value = "id") Long id){
        personServices.delete(id);
        return ResponseEntity.noContent().build();
    }
}