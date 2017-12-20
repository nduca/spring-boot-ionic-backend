package br.com.discover.estudosNathan.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.discover.estudosNathan.domain.Categoria;
import br.com.discover.estudosNathan.repositories.CategoriaRepository;
import br.com.discover.estudosNathan.services.exceptions.DataIntegrityException;
import br.com.discover.estudosNathan.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	// Automaticamente instanciada
	private CategoriaRepository repo;
	
	public Categoria find(Integer id) {
		Categoria obj = repo.findOne(id);
		if (obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado. id: " + id
					+ " , Tipo: " + Categoria.class.getName());
		}
		return obj;
	}
	
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Categoria update(Categoria obj) {
		find(obj.getId());
		return repo.save(obj);
		
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			
			repo.delete(id);
			
		}catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos");
			
		}
		
	}

}
