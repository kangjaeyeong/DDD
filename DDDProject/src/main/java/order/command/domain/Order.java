package order.command.domain;

import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

import common.model.Money;

@Entity
@Table(name = "purchase_order")
@Access(AccessType.FIELD)
public class Order {
	
	@Embedded
	private Orderer orderer;
	
    @Embedded
    private ShippingInfo shippingInfo;
	
    @EmbeddedId
	private OrderNo number;
    
    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    private OrderState state;
    
    @Column(name = "total_amounts")
    private Money totalAmounts;
    /**
     * MoneyConverter의 autoApply가 false이면 직접 지정해준다.
     * @Column(name = "total_amounts")
     * @Convert(converter = MoneyConverter.class)
     * private Money totalAmounts;
     */
    
    
    @ElementCollection
    @CollectionTable(name = "order_line", joinColumns = @JoinColumn(name = "order_number"))
    @OrderColumn(name = "line_idx")
    private List<OrderLine> orderLines;

    
	/**
	
    @Version
    private long version;



    @Column(name = "total_amounts")
    private Money totalAmounts;

    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;

    protected Order() {
    }

    public Order(OrderNo number, Orderer orderer, List<OrderLine> orderLines,
                 ShippingInfo shippingInfo, OrderState state) {
        setNumber(number);
        setOrderer(orderer);
        setOrderLines(orderLines);
        setShippingInfo(shippingInfo);
        this.state = state;
        this.orderDate = new Date();
        Events.raise(new OrderPlacedEvent(number.getNumber(), orderer, orderLines, orderDate));
    }

    private void setNumber(OrderNo number) {
        if (number == null) throw new IllegalArgumentException("no number");
        this.number = number;
    }

    private void setOrderer(Orderer orderer) {
        if (orderer == null) throw new IllegalArgumentException("no orderer");
        this.orderer = orderer;
    }

    private void setOrderLines(List<OrderLine> orderLines) {
        verifyAtLeastOneOrMoreOrderLines(orderLines);
        this.orderLines = orderLines;
        calculateTotalAmounts();
    }

    private void verifyAtLeastOneOrMoreOrderLines(List<OrderLine> orderLines) {
        if (orderLines == null || orderLines.isEmpty()) {
            throw new IllegalArgumentException("no OrderLine");
        }
    }

    private void calculateTotalAmounts() {
        this.totalAmounts = new Money(orderLines.stream()
                .mapToInt(x -> x.getAmounts().getValue()).sum());
    }

    private void setShippingInfo(ShippingInfo shippingInfo) {
        if (shippingInfo == null) throw new IllegalArgumentException("no shipping info");
        this.shippingInfo = shippingInfo;
    }

    public OrderNo getNumber() {
        return number;
    }

    public long getVersion() {
        return version;
    }

    public Orderer getOrderer() {
        return orderer;
    }

    public Money getTotalAmounts() {
        return totalAmounts;
    }

    public ShippingInfo getShippingInfo() {
        return shippingInfo;
    }

    public OrderState getState() {
        return state;
    }

    public void changeShippingInfo(ShippingInfo newShippingInfo) {
        verifyNotYetShipped();
        setShippingInfo(newShippingInfo);
        Events.raise(new ShippingInfoChangedEvent(number, newShippingInfo));
    }

    public void cancel() {
        verifyNotYetShipped();
        this.state = OrderState.CANCELED;
        Events.raise(new OrderCanceledEvent(number.getNumber()));
    }

    private void verifyNotYetShipped() {
        if (!isNotYetShipped())
            throw new AlreadyShippedException();
    }

    public boolean isNotYetShipped() {
        return state == OrderState.PAYMENT_WAITING || state == OrderState.PREPARING;
    }

    public List<OrderLine> getOrderLines() {
        return orderLines;
    }

    public boolean matchVersion(long version) {
        return this.version == version;
    }

    public void startShipping() {
        verifyShippableState();
        this.state = OrderState.SHIPPED;
        Events.raise(new ShippingStartedEvent(number.getNumber()));
    }

    private void verifyShippableState() {
        verifyNotYetShipped();
        verifyNotCanceled();
    }

    private void verifyNotCanceled() {
        if (state == OrderState.CANCELED) {
            throw new OrderAlreadyCanceledException();
        }
    }
	**/
}
