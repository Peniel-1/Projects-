import axios from 'axios';

export default {

  login(user) {
    return axios.post('/login', user)
  },

  register(user) {
    return axios.post('/register', user)
  },

  saveAddress(address) {
    return axios.post('/user/add-address', address);
  },

  getAddressBook(id) {
    return axios.get(`/user/${id}/address-book`);
  },

  savePayment(payment) {
    return axios.post('/user/add-payment', payment);
  },

  getPaymentOptions(id) {
    return axios.get(`/user/${id}/payments`);
  },

  deletePayment(id){
    return axios.delete(`/user/payments/${id}`);
  },
  deleteAddress(id){
    return axios.delete(`/user/address/${id}`);
  }

}
