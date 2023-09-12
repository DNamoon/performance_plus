package com.starter.performance.batch.reader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import org.springframework.batch.item.database.AbstractPagingItemReader;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.orm.JpaQueryProvider;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;

public class CustomJpaItemReader<T> extends AbstractPagingItemReader<T> {

    private EntityManagerFactory entityManagerFactory;

    private EntityManager entityManager;

    private final Map<String, Object> jpaPropertyMap = new HashMap<>();

    private String queryString;

    private JpaQueryProvider queryProvider;

    private Map<String, Object> parameterValues;

    private boolean transacted = true;

    public CustomJpaItemReader() {
        setName(ClassUtils.getShortName(JpaPagingItemReader.class));
    }

    private Query createQuery() {
        if (queryProvider == null) {
            return entityManager.createQuery(queryString);
        } else {
            return queryProvider.createQuery();
        }
    }

    public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public void setParameterValues(Map<String, Object> parameterValues) {
        this.parameterValues = parameterValues;
    }

    public void setTransacted(boolean transacted) {
        this.transacted = transacted;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        super.afterPropertiesSet();

        if (queryProvider == null) {
            Assert.notNull(entityManagerFactory, "EntityManager is required when queryProvider is null");
            Assert.hasLength(queryString, "Query string is required when queryProvider is null");
        }
    }

    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }


    public void setQueryProvider(JpaQueryProvider queryProvider) {
        this.queryProvider = queryProvider;
    }


    @Override
    protected void doOpen() throws Exception {
        super.doOpen();

        entityManager = entityManagerFactory.createEntityManager(jpaPropertyMap);
        if (entityManager == null) {
            throw new DataAccessResourceFailureException("Unable to obtain an EntityManager");
        }

        if (queryProvider != null) {
            queryProvider.setEntityManager(entityManager);
        }

    }


    @Override
    protected void doReadPage() {

        EntityTransaction tx = null;

        if (transacted) {
            tx = entityManager.getTransaction();
            tx.begin();

            entityManager.flush();
            entityManager.clear();
        }

        Query query = createQuery().setMaxResults(getPageSize());

        if (parameterValues != null) {
            for (Map.Entry<String, Object> me : parameterValues.entrySet()) {
                query.setParameter(me.getKey(), me.getValue());
            }
        }

        if (results == null) {
            results = new CopyOnWriteArrayList<>();
        } else {
            results.clear();
        }

        if (!transacted) {
            List<T> queryResult = query.getResultList();
            for (T entity : queryResult) {
                entityManager.detach(entity);
                results.add(entity);
            }
        } else {
            results.addAll(query.getResultList());
            tx.commit();
        }
    }

    @Override
    protected void doJumpToPage(int itemIndex) {

    }

    @Override
    protected void doClose() throws Exception {
        entityManager.close();
        super.doClose();
    }
}
