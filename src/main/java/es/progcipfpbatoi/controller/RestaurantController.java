package es.progcipfpbatoi.controller;

import es.progcipfpbatoi.exceptions.NotFoundException;
import es.progcipfpbatoi.utils.DateConversion;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.List;

//public class RestaurantController {
//
//    private InMemoryPendingOrderRepository pendingOrderRepository;
//    private InMemoryArchiveHistoryOrderRepository archiveHistoryOrderRepository;
//    private Waiter waiter;
//    public RestaurantController(InMemoryProductRepository productRepository, InMemoryPendingOrderRepository pendingOrderRepository, InMemoryArchiveHistoryOrderRepository archiveHistoryOrderRepository) {
//        this.pendingOrderRepository = pendingOrderRepository;
//        this.archiveHistoryOrderRepository = archiveHistoryOrderRepository;
//        this.waiter = new Waiter(productRepository);
//    }
//
//    /**
//     *  Registra un nuevo pedido en el restaurante
//     */
//    public void attendClient() {
//        Order order = waiter.attend(getNextOrderCode());
//        if (order != null) {
//            pendingOrderRepository.add(order);
//            System.out.println(AnsiColor.colorize(AnsiColor.GREEN, "Pedido registrado con éxito "));
//            showOrder(order);
//        } else {
//            System.out.println("Pedido no registrado por no tener ningún producto");
//        }
//    }
//
//    /**
//     * Lista todas los pedidos pendientes
//     */
//    public void listAllPendingOrders() {
//        listAllOrders(pendingOrderRepository);
//    }
//
//    /**
//     * Lista todas los pedidos del histórico
//     */
//    public void listAllServedOrders() {
//        listAllOrders(archiveHistoryOrderRepository);
//    }
//
//    private void listAllOrders(InMemoryArchiveHistoryOrderRepository orderRepository) {
//        if (orderRepository.size() == 0) {
//            System.out.println(AnsiColor.colorize(AnsiColor.RED, "No existen pedidos en el restaurante"));
//        } else {
//            OrderViewList orderViewList = new OrderViewList(orderRepository.findAll());
//            System.out.println(orderViewList);
//        }
//    }
//
//    /**
//     * Permite acceder a la información de un pedido pendiente
//     */
//    public void viewPendingOrder() {
//        listAllPendingOrders();
//        retrieveAndShowOrder(pendingOrderRepository);
//
//    }
//
//    /**
//     * Permite acceder a la información de un pedido del histórico
//     */
//    public void searchAndShowHistoricOrder() {
//        if (archiveHistoryOrderRepository.size() > 0) {
//            try {
//                LocalDateTime date = getValidDate();
//                List<Order> orders = archiveHistoryOrderRepository.getByDate(date);
//                System.out.println(new OrderViewList(orders));
//                Order order = getOrderByCod(archiveHistoryOrderRepository);
//                new OrderView(order).show();
//            } catch (NotFoundException ex) {
//                AnsiColor.errorOutput(ex.getMessage());
//            }
//        } else {
//            System.out.println(AnsiColor.colorize(AnsiColor.RED, "No existen pedidos en el histórico"));
//        }
//    }
//
//    private Order getOrderByCod(InMemoryArchiveHistoryOrderRepository orderRepository) throws NotFoundException {
//        String orderCode = GestorIO.obtenerString(AnsiColor.colorize(AnsiColor.HIGH_INTENSITY, "Introduzca el código del pedido que deseas visualizar"));
//        GestorIO.limpiar();
//        return orderRepository.getByCod(orderCode);
//    }
//
//    private LocalDateTime getValidDate() throws InputMismatchException{
//        do {
//            try {
//                String dateString = GestorIO.obtenerString(String.format("Introduzca la fecha del pedido en formato %s", AnsiColor.colorize(AnsiColor.HIGH_INTENSITY, "dd/mm/yyyy")));
//                return DateConversion.toLocalDateTime(dateString, 0, 0);
//            } catch (DateTimeParseException e) {
//                System.out.println("El formato introducido no es válido. Introdúzcalo de nuevo. Recuerde (dd/mm/yyyy)");
//            }
//        }while(true);
//    }
//
//    /**
//     * solicita al usuario el código del pedido a mostrar
//     *
//     */
//    private void retrieveAndShowOrder(InMemoryPendingOrderRepository pendingOrderRepository) {
//        if (pendingOrderRepository.size() > 0) {
//            try {
//                String orderCode = GestorIO.obtenerString(AnsiColor.colorize(AnsiColor.HIGH_INTENSITY, "Introduzca el código del pedido que deseas visualizar"));
//                Order order = pendingOrderRepository.getByCod(orderCode);
//                showOrder(order);
//            } catch (NotFoundException ex) {
//                AnsiColor.errorOutput(ex.getMessage());
//            }
//        }
//    }
//
//    /**
//     * Permite cancelar un pedido pendiente y que pase al listado de históricos
//     */
//    public void cancelPendingOrder() {
//        listAllPendingOrders();
//
//        if (pendingOrderRepository.size() > 0) {
//            try {
//                Order order = getOrderByCod(pendingOrderRepository);
//                order.setCanceled(GestorIO.obtenerTexto("¿Motivo de cancelación?"));
//                pendingOrderRepository.remove(order);
//                archiveHistoryOrderRepository.add(order);
//            } catch (NotFoundException ex) {
//                System.out.println(ex.getMessage());
//            }
//        }
//    }
//
//    /**
//     *  Permite marcar un pedido como servido, pasándolo al listado de históricos
//     */
//    public void prepareOrder() {
//        if (pendingOrderRepository.size() > 0) {
//            Order order = pendingOrderRepository.getNext();
//            OrderView orderView = new OrderView(order);
//            orderView.show();
//
//            if (!GestorIO.confirmar("¿Desea servir este pedido?")) {
//                pendingOrderRepository.relocate(order);
//            } else {
//                order.setServed();
//                archiveHistoryOrderRepository.add(order);
//                System.out.println(AnsiColor.colorize(AnsiColor.GREEN, "El pedido ha sido servido"));
//            }
//        }else {
//            System.out.println(AnsiColor.colorize(AnsiColor.RED, "No existen pedidos para ser preparados"));
//        }
//    }
//
//    /**
//     * Obtiene el proximo código de pedido
//     * @return
//     */
//    private String getNextOrderCode(){
//        return "o" + (pendingOrderRepository.size() + archiveHistoryOrderRepository.size() + 1);
//    }
//
//    /**
//     * Visualizar un pedido en formato tabla
//     */
//    private void showOrder(Order order) {
//        OrderView orderView = new OrderView(order);
//        orderView.show();
//    }
//}
