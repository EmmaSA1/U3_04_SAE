package utez.edu.mx.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import utez.edu.mx.Models.Cliente;
import utez.edu.mx.Repositories.ClienteRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public Cliente crearCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public List<Cliente> obtenerTodos() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> obtenerPorId(Long id) {
        return clienteRepository.findById(id);
    }

    public Cliente actualizar(Long id, Cliente cliente) {
        return clienteRepository.findById(id).map(c -> {
            c.setNombreCompleto(cliente.getNombreCompleto());
            c.setTelefono(cliente.getTelefono());
            c.setEmail(cliente.getEmail());
            return clienteRepository.save(c);
        }).orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
    }

    public void eliminar(Long id) {
        clienteRepository.deleteById(id);
    }
}
