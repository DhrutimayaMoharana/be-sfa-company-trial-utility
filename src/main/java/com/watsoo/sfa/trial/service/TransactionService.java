package com.watsoo.sfa.trial.service;

import com.watsoo.sfa.trial.dto.Response;
import com.watsoo.sfa.trial.model.Transaction;

public interface TransactionService {

	Response<?> transactionAdd(Transaction transaction);

}
