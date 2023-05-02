package com.cvp.oscarperez.service;

import com.cvp.oscarperez.dto.PersonDto;
import com.cvp.oscarperez.entities.Asset;
import com.cvp.oscarperez.entities.Person;
import com.cvp.oscarperez.repository.AssetRepository;
import com.cvp.oscarperez.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;


@Service
public class PersonService {
  @Autowired
  PersonRepository personRepository;
  @Autowired
  AssetRepository assetRepository;

  public List<Person> getAll() {
    return personRepository.findAll();
  }

  public Object addOne(PersonDto person) {
    if (person.getIdentification() == null) {
      return "Identification not provided";
    }
    if (personRepository.existsByIdentification(person.getIdentification())) {
      return "it already exists";
    }
    if (person.getAssets() != null && person.getAssets().size() > 0) {
      if(!assetRepository.existsByIdIn(person.getAssets().stream().map(Asset::getId).toList())) {
        return "Assets not found";
      }
    }

    Person personNew = Person.builder()
        .identification(person.getIdentification())
        .name(person.getName())
        .lastName(person.getLastName())
        .assets(person.getAssets())
        .build();
    return personRepository.save(personNew);
  }

  public Object updateOne(String id, PersonDto person) {
    if (!personRepository.existsById(id)) {
      return "Not found";
    }
    if (person.getAssets() != null && person.getAssets().size() > 0) {
      if(!assetRepository.existsByIdIn(person.getAssets().stream().map(Asset::getId).toList())) {
        return "Assets not found";
      }
    }
    Person personNew = Person.builder()
        .id(id)
        .identification(person.getIdentification())
        .name(person.getName())
        .lastName(person.getLastName())
        .assets(person.getAssets())
        .build();

    return personRepository.save(personNew);
  }
  public Object assignAssets(String id, PersonDto person) {
    if (!personRepository.existsById(id)) {
      return "Not found";
    }
    if(!assetRepository.existsByIdIn(person.getAssets().stream().map(Asset::getId).toList())) {
      return "Assets not found";
    }
    Person personOld = personRepository.findById(id).get();

    Person personNew = Person.builder()
        .id(id)
        .identification(personOld.getIdentification())
        .name(personOld.getName())
        .lastName(personOld.getLastName())
        .assets(person.getAssets())
        .build();

    return personRepository.save(personNew);
  }
}
