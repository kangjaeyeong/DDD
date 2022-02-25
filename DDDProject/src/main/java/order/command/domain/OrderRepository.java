package order.command.domain;

public interface OrderRepository {

	public Order findById(OrderNo no);
	public void save(Order order);
	
}
