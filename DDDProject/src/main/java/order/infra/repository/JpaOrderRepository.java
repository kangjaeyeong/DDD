package order.infra.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import order.command.domain.Order;
import order.command.domain.OrderNo;
import order.command.domain.OrderRepository;

@Repository
public class JpaOrderRepository implements OrderRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Order findById(OrderNo id) {
		// TODO Auto-generated method stub
		return entityManager.find(Order.class, id);
	}

	@Override
	public void save(Order order) {
		entityManager.persist(order);
		
	}

}
