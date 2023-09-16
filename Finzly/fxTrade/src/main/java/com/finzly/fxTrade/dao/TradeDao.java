package com.finzly.fxTrade.dao;

import java.util.Collections;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.finzly.fxTrade.model.Trade;

@Repository
public class TradeDao {

	private final SessionFactory sessionFactory;

	@Autowired
	public TradeDao(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void addTrade(Trade trade) {
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			session.save(trade);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Trade> getAllTrades() {
		try (Session session = sessionFactory.openSession()) {
			Criteria criteria = session.createCriteria(Trade.class);
			return criteria.list();

		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}
}
