package utez.edu.mx.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import utez.edu.mx.DTO.ClienteDto;
import utez.edu.mx.Models.Cliente;
import utez.edu.mx.Repositories.ClienteRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteDto crear(ClienteDto dto) {
        Cliente cliente = new Cliente();
        cliente.setNombreCompleto(dto.getNombreCompleto());
        cliente.setTelefono(dto.getTelefono());
        cliente.setCorreo(dto.getCorreo());
        Cliente saved = clienteRepository.save(cliente);

        dto.setId(saved.getId());
        return dto;
    }

    public List<ClienteDto> obtenerTodos() {
        return clienteRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    public ClienteDto obtenerPorId(Long id) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        return toDto(cliente);
    }

    public ClienteDto actualizar(Long id, ClienteDto dto) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        cliente.setNombreCompleto(dto.getNombreCompleto());
        cliente.setTelefono(dto.getTelefono());
        cliente.setCorreo(dto.getCorreo());
        clienteRepository.save(cliente);
        return toDto(cliente);
    }

    public void eliminar(Long id) {
        clienteRepository.deleteById(id);
    }

    private ClienteDto toDto(Cliente cliente) {
        ClienteDto dto = new ClienteDto();
        dto.setId(cliente.getId());
        dto.setNombreCompleto(cliente.getNombreCompleto());
        dto.setTelefono(cliente.getTelefono());
        dto.setCorreo(cliente.getCorreo());
        return dto;
    }
}
