package org.training360.spendor.transaction;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;

public class TransactionNotFoundException extends AbstractThrowableProblem {

    public TransactionNotFoundException() {
        super(URI.create("transactions/not-found"),
                "Not found",
                Status.NOT_FOUND,
                "Transactions not found.");
    }
}
