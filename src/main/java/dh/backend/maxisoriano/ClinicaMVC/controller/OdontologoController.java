package dh.backend.maxisoriano.ClinicaMVC.controller;

import dh.backend.maxisoriano.ClinicaMVC.entity.Odontologo;
import dh.backend.maxisoriano.ClinicaMVC.exception.ResourceNotFoundException;
import dh.backend.maxisoriano.ClinicaMVC.service.IOdontologoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/odontologo")
public class OdontologoController {
    public IOdontologoService odontologoService;
    public OdontologoController(IOdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }
    @PostMapping
    public ResponseEntity<Odontologo> registrarOdontologo(@RequestBody Odontologo odontologo){
        Odontologo odontologoARetornar = odontologoService.registrarOdontologo(odontologo);
        if(odontologoARetornar == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }else {
            return ResponseEntity.status(HttpStatus.CREATED).body(odontologoARetornar);
        }
    }
    @GetMapping
    public ResponseEntity<List<Odontologo>> buscarTodos(){
        return ResponseEntity.ok(odontologoService.buscarTodos());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Odontologo> buscarOdontologoPorId(@PathVariable Integer id){
        Optional<Odontologo> odontologo = odontologoService.buscarPorId(id);
        if(odontologo.isPresent()){
            Odontologo odontologoARetornar = odontologo.get();
            return ResponseEntity.ok(odontologoARetornar);
        }
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @PutMapping
    public ResponseEntity<String> actualizarOdontologo(@RequestBody Odontologo odontologo){
        Optional<Odontologo> odontologoOptional = odontologoService.buscarPorId(odontologo.getId());
        if (odontologoOptional.isPresent()) {
            odontologoService.actualizarOdontologo(odontologo);
            return ResponseEntity.ok("{\"message\": \"odontologo modificado\"}");
        } else {
            return new ResponseEntity<>("{\"message\": \"odontologo no encontrado\"}", HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> borrarOdontologo(@PathVariable Integer id) throws ResourceNotFoundException {
        odontologoService.eliminarOdontologo(id);
        return ResponseEntity.ok("{\"message\": \"odontologo eliminado\"}");
    }
    @GetMapping("/apellido/{apellido}")
    public ResponseEntity<List<Odontologo>> buscarPorApellido(@PathVariable String apellido){
        List<Odontologo> listaOdontologos = odontologoService.buscarPorApellido(apellido);
        if(listaOdontologos.size()>0){
            return  ResponseEntity.ok(listaOdontologos);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<List<Odontologo>> buscarPorNombre(@PathVariable String nombre){
        List<Odontologo> listaOdontologos = odontologoService.buscarPorNombre(nombre);
        if(listaOdontologos.size()>0){
            return  ResponseEntity.ok(listaOdontologos);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @PutMapping("/{id_odontologo}/especialidad/{id_especialidad}")
    public ResponseEntity<Odontologo> agregarEspecialidad(@PathVariable Integer id_odontologo,
                                                          @PathVariable Integer id_especialidad) throws ResourceNotFoundException {
        return ResponseEntity.ok(odontologoService.agregarEspecialidad(id_odontologo, id_especialidad));
    }
}
