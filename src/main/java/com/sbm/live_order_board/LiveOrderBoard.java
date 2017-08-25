package com.sbm.live_order_board;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import static java.lang.Math.negateExact;
import static java.util.Comparator.naturalOrder;
import static java.util.Comparator.reverseOrder;

public class LiveOrderBoard {

    // No orders history kept - just actual summary data, updated on every new order.
    // Two independent sections present (one for each type), since there is no merging/netting between different types.
    private final SortedMap<OrderType, SortedMap<Integer, Integer>> summary;

    public LiveOrderBoard() {
        summary = new TreeMap<>(naturalOrder());
        summary.put(OrderType.SELL, new TreeMap<>(naturalOrder()));
        summary.put(OrderType.BUY, new TreeMap<>(reverseOrder()));
    }

    public void registerOrder(Order order) {
        addQuantity(summary.get(order.getType()), order.getPrice(), order.getQuantity());
    }

    // We neither store order nor order id, so full object required for cancellation
    public void cancelOrder(Order order) {
        addQuantity(summary.get(order.getType()), order.getPrice(), negateExact(order.getQuantity()));
    }

    private void addQuantity(Map<Integer, Integer> summarySection, Integer price, Integer quantity) {
        summarySection.compute(price, (p, q) -> quantity + zeroIfNull(q));
    }

    private int zeroIfNull(Integer value) {
        return value == null ? 0 : value;
    }

    public List<SummaryEntry> getSummary() {
        List<SummaryEntry> result = new ArrayList<>();

        summary.forEach((type, section) ->
                section.forEach((price, quantity) ->
                        result.add(new SummaryEntry(type, price, quantity))
                )
        );

        return result;
    }
}
