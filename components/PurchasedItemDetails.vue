<template>
    <div class="cart-item">
        <!-- {{item}} {{numberOfOptions}} -->
        <!-- <p>{{item.item_type_name}}: <span>{{item.option_one_name}}</span>, {{item.item_option_two_name}} crust, {{item.item_option_three_name}} sauce -- ${{item.price}}</p> -->
        <p>{{optionString}}<span>---$</span>{{item.price}}</p>
        
        <!-- {{hasOptionOne}} {{hasOptionTwo}} {{hasOptionThree}} -->
        <span v-if="isPizza">Toppings-- {{toppingList()}}</span>
        <!-- <span v-for="topping in item.toppings" v-bind:key="topping.topping_id">{{topping.name}} ({{topping.topping_amount_name}}) </span> -->
    </div>
</template>

<script>
export default {
    name: "purchased-item-details",
    data() {
        return {

        }
    },
    props: {
        item: Object
    },
    computed: {
        isPizza() {
            return this.item.item_type_name == "Pizza";
        },
        hasOptionOne() {
            return this.item.item_option_one_name != null;
        },
        hasOptionTwo() {
            return this.item.item_option_two_name != null;
        },
        hasOptionThree() {
            return this.item.item_option_three_name != null;
        },
        numberOfOptions() {
            let hasOption = 0;
            if(this.hasOptionOne) {
                hasOption++;
            }
            if(this.hasOptionTwo) {
                hasOption++;
            }
            if(this.hasOptionThree) {
                hasOption++;
            }
            return hasOption;
        },
        optionString() {
            let string = this.item.item_type_name + ": " + this.item.name;
            let zeroth = true;
            let commas = this.numberOfOptions - 1;
            if(this.hasOptionOne) {
                if(zeroth) {
                    zeroth=false;
                    string += "  [";
                }
                string += this.item.item_option_one_name;
            }
            if(this.hasOptionTwo) {
                if(zeroth) {
                    zeroth=false;
                    string += "  [";
                }
                if(commas) {
                    commas--;
                    string += ", ";
                }
                string += this.item.item_option_two_name;
            }
            if(this.hasOptionThree) {
                if(zeroth) {
                    zeroth=false;
                    string += "  [";
                }
                if(commas) {
                    commas--;
                    string += ", ";
                }
                string += this.item.item_option_three_name;
            }
            if(!zeroth) {
                string += "]";
            }

            return string;
        }
    },
    methods: {
        toppingList() {
            let list = "";
            for (let index = 0; index < this.item.toppings.length; index++) {
                // console.log(this.item.toppings[index]);
                list += this.item.toppings[index].name + " (" + this.item.toppings[index].topping_amount_name + ")";
                if(index < this.item.toppings.length - 1) {
                    list += ", ";
                }
            }
            return list;
        }, 
        deleteItem(){
            
        }
    }
}
</script>

<style scoped>
.cart-item{
    color: rgb(17, 17, 17);
    font-size: 1em;
     margin: 3px;
  background-color: rgb(238, 211, 199);
  border-radius: 5px;

}

</style>