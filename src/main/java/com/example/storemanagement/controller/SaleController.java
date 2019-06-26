package com.example.storemanagement.controller;

import com.example.storemanagement.config.SpringSecurityAuditorAware;
import com.example.storemanagement.exception.ServiceException;
import com.example.storemanagement.model.Iterm;
import com.example.storemanagement.model.Sale;
import com.example.storemanagement.repository.ItermRepository;
import com.example.storemanagement.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/store")
public class SaleController {

  private final SaleRepository saleRepository;
  private final ItermRepository itermRepository;
  private final SpringSecurityAuditorAware springSecurityAuditorAware;

  @Autowired
  public SaleController(final SaleRepository saleRepository, final SpringSecurityAuditorAware springSecurityAuditorAware, final ItermRepository itermRepository) {
    super();
    this.saleRepository = saleRepository;
    this.springSecurityAuditorAware = springSecurityAuditorAware;
    this.itermRepository = itermRepository;
  }

  @RequestMapping(value = "/sale", method = RequestMethod.GET)
  public ResponseEntity<List<Sale>> getAllSale() {
    List<Sale> saleList;

    saleList = this.saleRepository.findAll();

    return new ResponseEntity<>(saleList, HttpStatus.OK);
  }

  @RequestMapping(value = "/sale", method = RequestMethod.POST)
  public void makeSale(@RequestBody Sale sale) {

    Iterm iterm = this.itermRepository.findByName(sale.getItermName());

    if (sale.getQuantity() > iterm.getQuantity()) {
      throw ServiceException.internalError("The is less than {0} Iterns in the store", sale.getQuantity());
    } else {
      Sale newsale = new Sale();

      newsale.setItermName(sale.getItermName());
      newsale.setQuantity(sale.getQuantity());
      newsale.setUnitPrice(iterm.getUnitPrice());
      newsale.setTotalAmount(sale.getTotalAmount());
      newsale.setSaleDate(new Date());
      newsale.setSaleBy(this.springSecurityAuditorAware.getCurrentAuditor().get());

      this.saleRepository.save(newsale);

      iterm.setQuantity(iterm.getQuantity() - newsale.getQuantity());

      this.itermRepository.save(iterm);
    }

  }
}
