package com.watsoo.sfa.trial.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.watsoo.sfa.trial.dto.Response;
import com.watsoo.sfa.trial.model.Transaction;
import com.watsoo.sfa.trial.repository.TransactionRepository;
import com.watsoo.sfa.trial.service.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private TransactionRepository transactionRepository;

	@Override
	public Response<?> transactionAdd(Transaction transaction) {
		try {
			if (transaction != null) {
				Transaction transactionSave = transactionRepository.save(transaction);
				return new Response<>(HttpStatus.CREATED.value(), HttpStatus.CREATED.name(), transactionSave);
			} else {
				return new Response<>(HttpStatus.BAD_REQUEST.value(), "Unable to save Transaction", null);
			}
		} catch (Exception e) {
			return new Response<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null);
		}
	}

}
