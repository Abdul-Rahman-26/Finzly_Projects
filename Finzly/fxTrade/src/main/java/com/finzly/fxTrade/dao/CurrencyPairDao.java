package com.finzly.fxTrade.dao;

import java.util.Collections;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.finzly.fxTrade.model.CurrencyPair;

@Repository
public class CurrencyPairDao {

	private final SessionFactory sessionFactory;

	@Autowired
	public CurrencyPairDao(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public String addCurrencyPair(CurrencyPair currencyPair) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Criteria criteria = session.createCriteria(CurrencyPair.class);
		criteria.add(Restrictions.eq("currencyPair", currencyPair.getCurrencyPair()));
		CurrencyPair existingCurrencyPair = (CurrencyPair) criteria.uniqueResult();

		if (existingCurrencyPair != null) {
			existingCurrencyPair.setRate(currencyPair.getRate());
			session.getTransaction().commit();
			session.close();
			return "CurrencyPair rate updated for CurrencyPair ID: " + existingCurrencyPair.getCurrencyPairId();
		} else {
			session.save(currencyPair);
			session.getTransaction().commit();
			session.close();
			return "Successfully added CurrencyPair in CurrencyPair ID "
					+ String.valueOf(currencyPair.getCurrencyPairId());
		}
	}

	public double getRateByCurrencyPair(String currencyPair) {
		try (Session session = sessionFactory.openSession()) {
			Criteria criteria = session.createCriteria(CurrencyPair.class);
			criteria.add(Restrictions.eq("currencyPair", currencyPair));
			criteria.setProjection(org.hibernate.criterion.Projections.property("rate"));
			Double rate = (Double) criteria.uniqueResult();
			return rate != null ? rate : 0;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public CurrencyPair getCurrencyPairByCurrency(String currencyPair) {
		try (Session session = sessionFactory.openSession()) {
			Criteria criteria = session.createCriteria(CurrencyPair.class);
			criteria.add(Restrictions.eq("currencyPair", currencyPair));
			return (CurrencyPair) criteria.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void updateCurrencyPairRate(CurrencyPair updatedPair) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();

		Criteria criteria = session.createCriteria(CurrencyPair.class);
		criteria.add(Restrictions.eq("currencyPair", updatedPair.getCurrencyPair()));
		CurrencyPair existingCurrencyPair = (CurrencyPair) criteria.uniqueResult();

		if (existingCurrencyPair != null) {
			existingCurrencyPair.setRate(updatedPair.getRate());
			transaction.commit();
		}

		session.close();
	}

	public List<CurrencyPair> getAllCurrencyPairs() {
		try (Session session = sessionFactory.openSession()) {
			Criteria criteria = session.createCriteria(CurrencyPair.class);
			return criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}
}
