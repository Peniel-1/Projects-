<template>
    <div id="checkout-view">
      <!-- {{$store.state.order}} -->
        <form>
          <div class="radio-container">
            <h3>Order Type:</h3>
            <input type="radio" v-on:change="changeOrderType" v-model="selected" name="orderType" value="1" id="PickUp">
            <label for="PickUp">Pick Up</label>
            <input type="radio" v-on:change="changeOrderType" v-model="selected" name="orderType" value="2" id="Delivery">
            <label for="Delivery">Delivery</label>
            <br/>
            <select id="addressSelect" v-model="selectedAddress" v-if="isAddressBookLoaded && isDelivery">
              <option v-for="address in addressBook" v-bind:key="address.users_address_id" :value="address.users_address_id">{{address.address_name}}</option>
              <option value="-1">Add New Address</option>
            </select>
            <address-form @reset-address="resetAddress" v-if="showAddressForm" />
          </div>

          <div class="radio-container">
            <h3>Payment Type:</h3>
            <input type="radio" v-on:change="changePaymentType" v-model="selectedPayment" name="paymentType" value="Cash" id="Cash">
            <label for="Cash">Cash</label>
            <input type="radio" v-on:change="changePaymentType" v-model="selectedPayment" name="paymentType" value="Credit" id="Credit">
            <label for="Credit">Credit Card</label>
            <br/>
            <select id="paymentSelect" v-model="selectedPaymentCard" v-if="isCardWalletLoaded && isCredit">
              <option v-for="card in cardWallet" v-bind:key="card.users_payment_id" :value="card.users_payment_id">{{card.payment_name}}</option>
              <option value="New">Add New Credit Card</option>
            </select>
            <credit-card-form @reset-card="resetPaymentCard" v-if="showCreditForm" />
          </div>
 
          <h4>Order Total: ${{$store.state.order.total}}</h4>
          <p>Total Number of Items: {{$store.state.order.items.length}}</p>
          <!-- {{$store.state.order}} -->
          <button v-on:click.prevent="submitOrder">Checkout</button>
          <button @click="resetOrder()">Reset Cart</button>
        </form
        >
        <show-order v-bind:order="currentOrder" />
    </div>
</template>

<script>
import AddressForm from '../components/AddressForm.vue';
import CreditCardForm from '../components/CreditCardForm.vue';
import ShowOrder from '../components/ShowOrder.vue';
import AuthService from '../services/AuthService';
import OrderService from '../services/OrderService.js';

export default {
    name: "checkout",
    components: {
        ShowOrder,
        AddressForm,
        CreditCardForm
    },
    computed: {    
      showAddressForm(){
        return this.selectedAddress == -1 && this.isDelivery;
      },
      showCreditForm(){
        return this.selectedPaymentCard == "New" && this.isCredit;
      },
      isDelivery() {
        return this.selected == 2;
      },
      isCredit() {
        return this.selectedPayment == "Credit";
      }
    },
    data() {
      return {
        selected: 1,
        selectedAddress: 1,
        selectedPayment: "Cash",
        selectedPaymentCard: 1,
        addressBook: [],
        isAddressBookLoaded: false,
        cardWallet: [],
        isCardWalletLoaded: false,
        currentOrder: {}
      }
    },
    methods: {
      resetOrder(){
        this.$store.commit("RESET_ORDER");
      },
      submitOrder() {
        const order = this.$store.state.order;
        this.$store.commit("SET_USER_ON_ORDER");
        OrderService.submitOrder(order).then ( response => {
          if (response.status === 200) {
            this.$store.commit("RESET_ORDER");
            this.$router.push({name:'home'});
          } else {
            console.log("status: " + response.status);
            console.log("headers: " + response.headers);
          }
        });
      },
      changeOrderType() {
        if(this.selectedAddress == -1) {
          this.resetAddress();
        }
        this.$store.commit("SET_DELIVERY_TYPE", this.selected);
      },
      changePaymentType() {
        if(this.selectedPayment == "New") {
          this.resetPaymentCard();
        }
        this.$store.commit("SET_PAYMENT_TYPE", this.selectedPayment);
      },
      resetAddress() {
        AuthService.getAddressBook(this.$store.state.user.id).then( response => {
          this.addressBook = response.data;
          this.isAddressBookLoaded = true;
          if(this.addressBook.length > 0) {
            this.selectedAddress = this.addressBook[this.addressBook.length-1].users_address_id;
          }
        });
      },
      resetPaymentCard() {
        console.log("resetting wallet");
        AuthService.getPaymentOptions(this.$store.state.user.id).then( response => {
          this.cardWallet = response.data;
          this.isCardWalletLoaded = true;
          if(this.cardWallet.length > 0) {
            this.selectedPaymentCard = this.cardWallet[this.cardWallet.length-1].users_payment_id;
          }
        });
      }
    },
    created() {
      this.$store.commit("SET_USER_ON_ORDER");
      console.log("getting address book");
      AuthService.getAddressBook(this.$store.state.user.id).then( response => {
        this.addressBook = response.data;
        this.isAddressBookLoaded = true;
        if(this.addressBook.length == 0) {
          this.selectedAddress = -1;
        } else {
          this.selectedAddress = this.addressBook[this.addressBook.length-1].users_address_id;
        }
      });
      console.log("getting wallet");
      AuthService.getPaymentOptions(this.$store.state.user.id).then( response => {
        this.cardWallet = response.data;
        this.isCardWalletLoaded = true;
        if(this.cardWallet.length == 0) {
          this.selectedPaymentCard = "New";
        } else {
          this.selectedPaymentCard = this.cardWallet[this.cardWallet.length - 1].users_payment_id;
        }
      });
      console.log("getting labels on order");
      OrderService.getOrderLabels(this.$store.state.order).then( response => {
        this.currentOrder = response.data;
      });
      console.log("load done");
    },
    updated() {
      AuthService.getAddressBook(this.$store.state.user.id).then( response => {
        this.addressBook = response.data;
        this.isAddressBookLoaded = true;
      });
      
      AuthService.getPaymentOptions(this.$store.state.user.id).then( response => {
        this.cardWallet = response.data;
        this.isCardWalletLoaded = true;
      });
    }
}
</script>

<style scoped>
.radio-container{
  margin: 5px;
  padding: 5px;
  min-height: 50px;
  border-bottom: 1px black solid;
}
#checkout-view {
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
}
</style>