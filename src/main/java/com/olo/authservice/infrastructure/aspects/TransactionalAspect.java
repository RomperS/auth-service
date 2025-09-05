package com.olo.authservice.infrastructure.aspects;

import com.olo.authservice.common.anotations.CustomTransactional;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@Aspect
@Component
@RequiredArgsConstructor
public class TransactionalAspect {

    private final PlatformTransactionManager transactionManager;

    @Around("@annotation(customTransactional)")
    public Object manageTransaction(ProceedingJoinPoint joinPoint, CustomTransactional customTransactional) throws Throwable {

        boolean isReadOnly = customTransactional.readOnly();

        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        def.setReadOnly(isReadOnly);

        TransactionStatus status = transactionManager.getTransaction(def);
        try {
            Object result = joinPoint.proceed();
            transactionManager.commit(status);
            return result;
        } catch (Exception e) {
            transactionManager.rollback(status);
            throw e;
        }
    }
}