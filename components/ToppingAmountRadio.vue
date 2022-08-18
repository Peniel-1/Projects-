<template>
    <div>
        <form class="select-container">
            <select id="topping-amount-select" v-model="value" @change="onSelectChange">
                <option v-for="opt in options" :value="opt.id" v-bind:key="opt.id">{{opt.name}}</option>
            </select>
        </form>
        <!-- {{returnObject}} -->
    </div>
</template>

<script>
export default {
    name: "topping-amount-radio",
    props: {
        toppingId: Number,
        options: Array
    },
    data() {
        return {
            value: 2
        }
    },
    computed: {
        returnObject() {
            return {
                    topping_id: this.toppingId,
                    topping_amount: this.value
            }
        }
    },
    methods: {
        onSelectChange() {
            this.$emit('topping-event', this.returnObject);
        }
    },
    // This worked for initialization, but wouldn't remove the return object
    // created() {
    //     this.$emit('topping-event', this.returnObject);
    // }
}
</script>

<style scoped>
.select-container {
    padding-left: 1vw;
}
</style>