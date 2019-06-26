package com.example.storemanagement.controller;

import com.example.storemanagement.exception.ServiceException;
import com.example.storemanagement.model.Iterm;
import com.example.storemanagement.repository.ItermRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/store")
public class ItermController {

  private final ItermRepository itermRepository;

  @Autowired
  public ItermController(final ItermRepository itermRepository) {
    super();
    this.itermRepository = itermRepository;
  }

  @RequestMapping(value = "iterm", method = RequestMethod.GET)
  public ResponseEntity<List<Iterm>> getAllIterm() {
    List<Iterm> itermList;
    itermList = this.itermRepository.findAll();
    return new ResponseEntity<>(itermList, HttpStatus.OK);
  }

  @RequestMapping(value = "iterm/{iternId}", method = RequestMethod.DELETE)
  public void deleteIterm(@PathVariable("iternId") String iternId) {

    Optional<Iterm> optionalIterm = this.itermRepository.findById(iternId);

    if (optionalIterm.isPresent()) {
      this.itermRepository.delete(optionalIterm.get());

    } else {
      throw ServiceException.conflict("the iterm {0} dont exists.", optionalIterm.get().getName());
    }
  }


  @RequestMapping(value = "iterm", method = RequestMethod.POST)
  public void addIterm(
          @RequestBody Iterm iterm) throws IOException {

    if (iterm.getName() != null && this.itermRepository.itermExistsByName(iterm.getName())) {
      throw ServiceException.conflict("Iterm {0} already exists.", iterm.getName());
    } else {
//      iterm.setFileName(file.getOriginalFilename());
//      iterm.setPicture(file.getBytes());
      this.itermRepository.save(iterm);
    }

  }


  @RequestMapping(value = "iterm", method = RequestMethod.PUT)
  public void updateIterm(@RequestParam("file") MultipartFile file,
                          @RequestParam("iterm") Iterm iterm) throws IOException {

    iterm.setFileName(file.getOriginalFilename());
    iterm.setPicture(file.getBytes());
    this.itermRepository.save(iterm);
  }

}
