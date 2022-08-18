<template>
    <div>
        <div class="option-container" v-for="opt in options" v-bind:key="opt.name">
            <div class="topping-label">{{opt.name}}</div>
            <div class="input-container">
                <div class="button-container">
                    <button class= "button1" v-on:click.prevent="toggleAvailability(opt.id)">{{opt.isAvailable ? "Available" : "Not Available"}}</button>
                    <button class= "button2" v-on:click.prevent="togglePremium(opt)">{{opt.isPremium ? "Premium" : "Regular"}}</button>
                </div>
                <div class="value-container">
                    <input v-on:change.prevent="updatePremium(opt)" type="number" min="0.01" step="0.01" name="price" v-model="opt.price" v-show="opt.isPremium">
                </div>
            </div>
            
        </div>
    </div>
</template>

<script>
import PizzaService from '../services/PizzaService.js';

export default {
    name:"change-menu",
    data() {
        return {  
            toggle: false
        }
    },
    props: {
        title: String,
        options: Array
    },
    methods: {
        toggleAvailability(id) {
            if(this.title == "Toppings") {
                PizzaService.toggleToppingAvailability(id).then(() => {
                    this.$emit('refresh');
                });
            } else {
                PizzaService.toggleOptionAvailability(id).then(() => {
                    this.$emit('refresh');
                });
            }
        },
        togglePremium(opt) {
            if(this.title == "Toppings") {
                const newOption = {
                    topping_id: opt.id,
                    topping_name: opt.name,
                    topping_is_available: opt.isAvailable,
                    topping_is_premium: !opt.isPremium,
                    topping_premium_price: opt.price
                };
                if(newOption.topping_premium_price == null) { newOption.option_premium_value = 0; }
                PizzaService.changeToppingPremium(opt.id, newOption).then(() => {
                    this.$emit('refresh');
                });
            } else {
                const newOption = {
                    itemOptionId: opt.id,
                    itemTypeId: 1,
                    itemOptionTypeId: null,
                    optionId: null,
                    option: opt.name,
                    is_available: opt.isAvailable,
                    is_premium: !opt.isPremium,
                    price: opt.price
                };
                if(newOption.option_premium_value == null) { newOption.option_premium_value = 0; }
                PizzaService.changeOptionPremium(opt.id, newOption).then(() => {
                    this.$emit('refresh');
                });
            }
        },
        updatePremium(opt) {
            if(this.title == "Toppings") {
                const newOption = {
                    topping_id: opt.id,
                    topping_name: opt.name,
                    topping_is_available: opt.isAvailable,
                    topping_is_premium: opt.isPremium,
                    topping_premium_price: opt.price
                };
                if(newOption.topping_premium_price === undefined) { newOption.topping_premium_price = 0; }
                PizzaService.changeToppingPremium(opt.id, newOption).then(() => {
                    this.$emit('refresh');
                });
            } else {
                const newOption = {
                    itemOptionId: opt.id,
                    itemTypeId: 1,
                    itemOptionTypeId: null,
                    optionId: null,
                    option: opt.name,
                    is_available: opt.isAvailable,
                    is_premium: opt.isPremium,
                    price: opt.price
                };
                if(newOption.option_premium_value == null) { newOption.option_premium_value = 0; }
                PizzaService.changeOptionPremium(opt.id, newOption).then(() => {
                    this.$emit('refresh');
                });
            }
        }
    },
    created() {
        this.newPrice = this.options.price;
    }
}
</script>

<style scoped>
* {
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
}

button {
    border-radius: 5px;
}

.title-button-container {
    display: flex;
    align-content: center;
    align-items: center;
}

.title-button-container > button {
    height: auto;
    margin-left: 1.5vw;
}

.title {
    color: black;
    font-size: 3vw;
}

.option-container {
    display: flex;
    height: auto;
    justify-content: space-between;
}

.input-container {
    display: flex;
    justify-content: flex-end;
}

.button-container {
    display: flex;
    justify-content: space-between;
    min-width: 8vw;
}

.value-container {
    min-width: 5vw;
}

.value-container > input {
    width: 4vw;
}

.topping-label {
    display: flex;
    align-items: center;
    justify-items: left;
    height: auto;
}
.button1{
    border: 1px solid black;
    font-size: 1.3vw;
    border-radius: 12px;
    min-width: 5vw;
    padding: 0 1vw;
    width: 10vw;
}
.button2 {
border: 1px solid black;
font-size: 1em;
border-radius: 12px;
width: 7vw;
}
</style>