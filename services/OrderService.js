import axios from 'axios';

const http = axios.create({
    baseURL: "http://localhost:9000/"
});

export default {
    getAll() {
        return http.get('/orders');
    },

    getOrderLabels(order) {
        return http.put('orders/current-order', order);    
    },
    
    CreateOrder(order) {
        return http.post('/orders', order)
    },
     cancelOrder(id) {
         return http.put(`/orders/currentorders/${id}`)
     },
    
    updateOrderStatus(orderid, statusid){
        return http.put(`/orders/${statusid}/orderstatus/${orderid}`)
    },

    getActiveOrders(){
    return http.get('/orders/activeorders/')
    },

    getFindOrder(id) {
        return http.get(`/orders/${id}`);
    },

    getOrdersByStatus(id) {
        return http.get(`/orders/status/${id}`);
    },

    submitOrder(order) {
        return http.post(`/orders`, order);
    },
    listCustomerOrders(order_user_id){
        return http.get(`/orders/user/${order_user_id}`);
    }
}