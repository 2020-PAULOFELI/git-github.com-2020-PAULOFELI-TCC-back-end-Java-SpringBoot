package com.systempro.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.systempro.domain.Fornecedor;
import com.systempro.repositories.FornecedorRepository;
import com.systempro.services.exceptions.DataIntegrityException;
import com.systempro.services.exceptions.ObjectNotFoundException;

@Service
public class FornecedorService {

	@Autowired
	private FornecedorRepository repo; // importe do repositorio

	// faz a busca por uma fornecedor atraves de um id
	public Fornecedor find(Integer id) {
		Optional<Fornecedor> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! ID: " + ", Tipo: " + FornecedorService.class.getName()));
	}

	public Fornecedor insert(Fornecedor obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	public Fornecedor update(Fornecedor obj) {
		Fornecedor newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	private void updateData(Fornecedor newObj, Fornecedor obj) {
		newObj.setNome(obj.getNome());
	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos.");
		}
	}

	public List<Fornecedor> findAll() {
		return repo.findAll();
	}

}
