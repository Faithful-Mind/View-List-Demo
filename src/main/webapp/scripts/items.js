const url = "ajax/items";

const vm = new Vue({
    el: '#app',
    data () {
        return {
            items: []
        };
    },
    mounted () {
        axios.get(url).then(response => {
            this.items = response.data;
        });
        // // Fetch API version
        // fetch(url)
        //     .then(response => response.json())
        //     .then(value => {
        //         this.items = value;
        //     })
        //     .catch(error => console.log(error));
    }
});