<template>
    <div id="background" v-if="!this.isLoading">
        <!-- {{order}} -->
        <form v-on:submit.prevent="addPizza()">
            <div class="subheader">
                    <h4 class="description">{{pizzaDetails.description}}</h4>
                    <input class="submit" type="submit" v-if="isUser" :value="'Add to cart: $' + updateTotal()" />
                <!-- <h6 class="price">Price: ${{updateTotal()}}</h6> -->
            </div>
            <div class="option-form-container">
                <form class="option-form" v-if="isUser && hasOptionOne">
                    <p class="option-title">Size:</p>
                    
                    <div class="single-option-container" v-for="option in this.sizeOptions" v-bind:key="option.name.itemOptionId">
                        <input class="radio-button" v-if="option.name.available" :id="option.name.itemOptionId" type="radio" v-bind:value="option.index" v-model="order.item_option_one_id" />
                        <div class="label-price-container">
                            <label class="option-label" v-if="option.name.available" :for="option.name.itemOptionId">{{option.name.option}}</label>
                            <span class="premium-price" v-if="option.name.available && option.name.premium">+${{option.name.price}}</span>
                        </div>
                    </div>
                </form>

                <form class="option-form" v-if="isUser && hasOptionTwo">
                    <p class="option-title">Crust:</p>
                    <div class="single-option-container" v-for="option in crustOptions" v-bind:key="option.name.itemOptionId">
                        <input class="radio-button" v-if="option.name.available" :id="option.name.itemOptionId" type="radio" v-bind:value="option.index" v-model="order.item_option_two_id" />
                        <div class="label-price-container">
                            <label class="option-label" v-if="option.name.available" :for="option.name.itemOptionId">{{option.name.option}}</label>
                            <span class="premium-price" v-if="option.name.available && option.name.premium">+${{option.name.price}}</span>
                        </div>
                    </div>
                </form>

                <form class="option-form" v-if="isUser && hasOptionThree">
                    <p class="option-title">Sauce:</p>
                    <div class="single-option-container" v-for="option in sauceOptions" v-bind:key="option.name.itemOptionId">
                        <input class="radio-button" v-if="option.name.available" :id="option.name.itemOptionId" type="radio" v-bind:value="option.index" v-model="order.item_option_three_id" />
                        <div class="label-price-container">
                            <label class="option-label" v-if="option.name.available" :for="option.name.itemOptionId">{{option.name.option}}</label>
                            <span class="premium-price" v-if="option.name.available && option.name.premium">+${{option.name.price}}</span>
                        </div>
                    </div>
                </form>
            </div>

            <form class="topping-form" v-if="!pizzaDetails.is_specialty && isUser && isPizza">
                <p class="option-title">Toppings:</p>
                <div class="topping-container">
                    <div class="single-topping-container" v-for="option in toppingOptions" v-bind:key="option.name">
                        <input v-if="option.name" :id="option.name" type="checkbox" v-bind:value="option.index" v-model="chosenToppings" v-on:click="initializeTopping(option.index)" />
                        <div class="label-select-container">
                        <div class="label-price-container">
                            <label class="topping-label" v-if="option.name" :for="option.name">{{option.name}}</label>
                            <span class="premium-price" v-if="option.name && option.price > 0">+${{option.price}}</span>

                        </div>
                        <div class="topping-options-container">
                            <topping-amount-radio @topping-event="onChangeChild" v-bind:toppingId="option.index" v-bind:options="toppingAmountOptions" v-show="showAmount(option.index)" />
                        </div>
                        </div>
                    </div>
                </div>
            </form>
            <!-- <h4>This will be passed to the API: {{this.order}}</h4> --> 
        </form>
    </div>
</template>
<script>
    import PizzaService from '../services/PizzaService.js';
    import ToppingAmountRadio from './ToppingAmountRadio.vue';
    // import Review from './Review.vue'
    
    export default { 
        name: "pizza-details",
        components: {
            ToppingAmountRadio
        },
        data() {
            return {
                isLoading: true,
                order: {
                    item_type_id: this.pizzaDetails.item_type_id,
                    name: this.pizzaDetails.name,
                    price: this.pizzaDetails.price,
                    item_option_one_id: 1,
                    item_option_two_id: 1,
                    item_option_three_id: 1,
                    toppings: []
                },
                sizeOptions: [],
                crustOptions: [],
                sauceOptions: [],
                toppingOptions: [],
                toppingAmountOptions: [],
                chosenToppings: [],
                chosenToppingsAmount: []
            };
        },
        computed: {
            isUser() {
                if(this.$store.state.user.id === undefined) {
                    return false;
                }

                return this.$store.state.user.authorities.find( b => b.name == "ROLE_USER") != null;
            },
            isPizza() {
                return this.pizzaDetails.item_type_id === 1;
            },
            hasOptionOne() {
                return this.sizeOptions.length > 0;
            },
            hasOptionTwo() {
                return this.crustOptions.length > 0;
            },
            hasOptionThree() {
                return this.sauceOptions.length > 0;
            }
        },
        props: {
            pizzaDetails: Object
        },
        created() {
            // console.log("loading toppings");
            if(this.isPizza) {
                PizzaService.getByoToppings().then( response => {
                    const optionArray = [];
                    this.toppingOptions = optionArray;

                    for (let i = 0; i < response.data.length; i++) {
                        
                        const element = {
                            name: response.data[i].name,
                            index: response.data[i].id,
                            isPremium: response.data[i].isPremium,
                            price: response.data[i].topping_premium_price,
                            isAvailable: true
                            };
                        optionArray.push(element);
                    }
                });
            }
            // console.log("loading option 1");
            PizzaService.getOptions(this.pizzaDetails.item_type_id,1).then( response => {
                var optionArray = [];

                for (let i = 0; i < response.data.length; i++) {
                    const element = {
                        name: response.data[i],
                        index: i+1
                        };
                    optionArray.push(element);
                }

                this.sizeOptions = optionArray;
            });
            // console.log("loading option 2");
            PizzaService.getOptions(this.pizzaDetails.item_type_id,2).then( response => {
                var optionArray = [];

                for (let i = 0; i < response.data.length; i++) {
                    const element = {
                        name: response.data[i],
                        index: i+1
                        };
                    optionArray.push(element);
                }

                this.crustOptions = optionArray;
            });
            // console.log("loading option 3");
            PizzaService.getOptions(this.pizzaDetails.item_type_id,3).then( response => {
                var optionArray = [];

                for (let i = 0; i < response.data.length; i++) {
                    const element = {
                        name: response.data[i],
                        index: i+1
                        };
                    optionArray.push(element);
                }

                this.sauceOptions = optionArray;
            });

            PizzaService.getSpecialtyToppings(this.$route.params.itemName).then( response => {
                
                response.data.forEach( element => { // Element is just a String
                    this.order.toppings.push({
                        topping_id: element,
                        topping_amount: 2
                    });
                });   
            });
            
            // console.log("loading topping amounts");
            if(this.isPizza) {
                PizzaService.getToppingAmounts().then( response => {
                    this.toppingAmountOptions = response.data;
                });
            }
            this.isLoading = false;
            // console.log("done loading");
        },
        methods: {
            addPizza() {
                this.order.price = this.updateTotal(); 
                if(!this.hasOptionOne) {
                    this.order.item_option_one_id = 0;
                }
                if(!this.hasOptionTwo) {
                    this.order.item_option_two_id = 0;
                }
                if(!this.hasOptionThree) {
                    this.order.item_option_three_id = 0;
                }
                this.$store.commit("ADD_TO_ORDER", this.order);
                this.$router.push({name:'menu'});
            },
            showAmount(id) {
                
                if(this.chosenToppings.find(element => element === id)) {
                    return true;
                }
                return false;
            },
            onChangeChild(value) {
                // This should only be triggered while an object with topping_id exists, hopefully
                this.order.toppings.filter( b => {
                    if(b.topping_id === value.topping_id) {
                        b.topping_amount = value.topping_amount;
                    }
                });
            },
            initializeTopping(id) {
                if(this.order.toppings.find( b => b.topping_id === id ) === undefined){
                    this.order.toppings.push({ topping_id: id, topping_amount: 2 });
                } else {
                    this.order.toppings = this.order.toppings.filter( b => {
                        return b.topping_id !== id;
                    });
                }
            },
            updateTotal(){
                const basePrice = this.pizzaDetails.price;
                let optionOnePrice = 0;
                // console.log("Has option 1: " + this.hasOptionOne);
                if(this.hasOptionOne) {
                    optionOnePrice = this.sizeOptions.find(b =>{
                        if(b.index == this.order.item_option_one_id){
                            return b;
                        }
                    }).name.price;
                }
               let optionTwoPrice = 0;
            //    console.log("Has option 2: " + this.hasOptionTwo);
               if(this.hasOptionTwo) {
                    optionTwoPrice = this.crustOptions.find(b =>{
                        if(b.index == this.order.item_option_two_id){
                            return b;
                        }
                    }).name.price;
               }
               
               let optionThreePrice = 0;
            //    console.log("Has option 3: " + this.hasOptionThree);
               if(this.hasOptionThree) {
                    optionThreePrice = this.sauceOptions.find(b =>{
                        if(b.index == this.order.item_option_three_id){
                            return b;
                        }
                    }).name.price;
               }
                return Math.round((basePrice + optionOnePrice + optionTwoPrice + optionThreePrice + this.toppingPrice() + Number.EPSILON) * 100) / 100;
            },
            toppingPrice(){
                let i = 0;
                let sum = 0;
                if(!this.isPizza) {
                    return 0;
                }

                for(i=1; i <= this.toppingOptions.length; i++){
                    if(this.chosenToppings.includes(i)){
                        sum += this.toppingOptions.find(b => {
                            if(b.index == i){
                                return b;
                            }
                        }).price;
                    }
                } return sum;
            }
        }
    }
</script>    
<style>
#background {
    margin: 0 5px;
}

.subheader {
    display: flex;
    justify-content: flex-start;
    padding: 0.25vw;
}

.submit {
    margin-left: 2vw;
}

.description {
    margin: 0;
    display: flex;
    align-items: center;
}

.option-form-container, .topping-form {
    display: flex;
    padding: 0 3vw;
}

.option-form-container {
    justify-content: space-between;
}

.topping-form {
    display: flex;
    flex-direction: column;
}

.option-title{
    margin: 10px auto;
    text-align: left;
    text-shadow: -1px -1px 0px rgba(255, 255, 255,0.3), 1px 1px 0px rgba(0,0,0,0.8);
    color: #333;
    opacity: .8;
    font: 700 4vw 'Bitter'; 
}

.option-form {
    min-width: 20vw;
    max-width: 32vw;
    margin: 0;
}

.topping-form {
    margin: 0;
    flex-wrap: wrap;
}

.single-option-container{
    display: flex;
    flex-wrap: nowrap;
    justify-content: flex-start;
}

.topping-container {
    display: flex;
    height: 20vh;
    flex-wrap: wrap;
    flex-direction: column;
}

.single-topping-container{
    display: flex;
    width: 32vw;
}

.label-select-container {
    display: flex;
    justify-content: space-between;
}

.label-price-container {
    display: flex;
    flex-wrap: nowrap;
    justify-content: space-between;
    padding-left: 1vw;
    flex: 0 1 auto;
}

.option-label, .topping-label {
    /* display: flex; */
    flex-wrap: nowrap;
    white-space: nowrap;
    text-align: left;
    color: rgb(32, 5, 5);
}

.option-label {
    padding-right: 1vw;
    font: 400 2.5vw 'Bitter';
}

.topping-label {
    padding-right: 0.5vw;
    font: 400 2vw 'Bitter';
}

.premium-price {
    display: inline-flex;
    align-items: center;
    flex-wrap: nowrap;
    font: 300 2vw 'Bitter';
    font-style: italic;
    /* padding-right: 1.5vw; */
}

.radio-button {
    margin: 0;
    width: 2.6vw;
    padding-left: 1vw;
}

 </style> 