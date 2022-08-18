import axios from 'axios';

const http = axios.create({
    baseURL: "http://localhost:9000/"
});

export default {
    getMenu() {
        return http.get('/menu')
    },
    getOptions(item_type_id, option_id) {
        return http.get(`/menu/${item_type_id}/${option_id}`)
    },
    getSpecialtyToppings(name) {
        return http.get(`/menu/pizza/${name}`);
    },
    getByoToppings() {
        return http.get('/menu/byo-toppings')
    },
    getToppingsDetail() {
        return http.get('/menu/toppings');
    },
    toggleToppingAvailability(id){
        return http.put(`menu/1/toggle-topping/${id}`)
    },
    changeToppingPremium(id, option){
        console.log("topping toggle");
        return http.put(`menu/1/price-topping/${id}`, option)
    },
    toggleOptionAvailability(id){
        return http.put(`menu/1/toggle-option/${id}`);
    },
    changeOptionPremium(id, option){
        console.log("option toggle");
        console.log(option);
        return http.put(`/menu/1/price-option/${id}`, option);
    },
    getToppingAmounts() {
        return http.get('/menu/topping-amount');
    },
    getSpecialtyPizza(){
        return http.get('/menu/specialty');
    },
    toggleSpecialtyPizza(id){
        return http.put(`/menu/1/toggle-pizza/${id}`)
    },
    addSpecialtyPizza(pizza){
        return http.post('/menu/1/add-pizza', pizza)
    },
    deleteSpecialtyPizza(id){
        return http.delete(`/menu/1/delete-pizza/${id}`)
    }
}
