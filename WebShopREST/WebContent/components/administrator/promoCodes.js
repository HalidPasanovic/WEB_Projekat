Vue.component("promo-codes", { 
	data: function () {
	    return {
	      username : "",
	      password : "",
	      name : "",
	      surname : "",
	      gender : "",
	      mode: "IDLE",
	      mode2 : "IDLE",
	      temp: null,
	      expiration : null,
	      amount : null,
	      percentage : null
	    }
	},
	    template: ` 
    	<div>
    		<table>
			<tr><td>Expiration Date:</td><td><input type="date" name="expiration" id="myDate" v-model="expiration"></td></tr>
			<tr><td>Amount Of Usage:</td><td><input type="text" name="amount" v-model="amount"></td></tr>
			<tr><td>Percentage:</td><td><input type="text" name="percentage" v-model="percentage"></td></tr>
			<tr><td><input type="submit" value="Create" v-on:click = "Create"></td></tr>
		</table>
		<p v-bind:hidden="mode == 'IDLE'" style="color:red">Form not inputed correctly</p>
		<p v-bind:hidden="mode2 == 'IDLE'">PromoCode created!</p>
    	</div>		  
    	`,
    mounted () {
			var current = new Date();
			let s = current.getFullYear() + '-'
			if((current.getUTCMonth() + 1) < 10)
			{
				s = s + '0' + (current.getUTCMonth() + 1) + '-'
			}
			else
			{
				s = s + (current.getUTCMonth() + 1) + '-'
			}
			if((current.getUTCDate()) < 10)
			{
				s = s + '0' + (current.getUTCDate())
			}
			else
			{
				s = s + (current.getUTCDate())
			}
		 	document.getElementById("myDate").min = String(s);
    },
    
    methods: {
		Create : function(){
			this.mode = "IDLE";
			this.mode2 = "IDLE";
			axios
			.post('rest/promocodes/', {"howLongItWorksDate":''+this.expiration,"ammountOfUsage":''+this.amount, "discountPercentage":''+this.percentage})
				.then(response => (this.mode2 = "ACCEPTED"))
				.catch((e) => { this.mode = "REJECT" })
			}
	}
});