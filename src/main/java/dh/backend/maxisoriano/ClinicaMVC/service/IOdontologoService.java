package dh.backend.maxisoriano.ClinicaMVC.service;

import dh.backend.maxisoriano.ClinicaMVC.entity.Odontologo;
import dh.backend.maxisoriano.ClinicaMVC.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

public interface IOdontologoService {
    Odontologo registrarOdontologo(Odontologo odontologo);
    Optional<Odontologo> buscarPorId(int id);
    List<Odontologo> buscarTodos();
    void actualizarOdontologo(Odontologo odontologo);
    void eliminarOdontologo(Integer id) throws ResourceNotFoundException;
    List<Odontologo> buscarPorApellido(String apellido);
    List<Odontologo> buscarPorNombre(String nombre);
    //Agregar especialidades
    Odontologo agregarEspecialidad(Integer id_odontologo, Integer id_especialidad) throws ResourceNotFoundException;
}
