Vue.component("membership", {
    data: function () {
        return {
            user: {},
            membershipTypes: null,
            selectedMembershipType: null,
            promoCodePercentage: 0,
            promoCodeString: null,
            membership: {}
        }
    },
    template: ` 
    <div class="d-flex flex-nowrap">
        <div class="d-flex flex-nowrap" style="width: 100%; margin-top: 2%;">
            <div style="width: 500px">
                <div>
                    <label for="Type" class="form-label">Type of membership</label>
                    <select v-model = "selectedMembershipType" class="form-control">
						<option v-for="(f, index) in membershipTypes" :value = "f.id">{{f.type}}</option>
					</select>
                </div>
                <div v-if="selectedMembershipType != null" style="margin-top: 50px;"> 
                    <div>
                        <label for="Type" class="form-label">Price</label>
                        <input type="text" class="form-control" :value = "GetPrice" id="Type" placeholder="" disabled>
                    </div>
                    <div>
                        <label for="Name" class="form-label">Number of visits</label>
                        <input type="number" class="form-control" id="Name" :value = "GetSelection().visitationCount" placeholder="" disabled>
                    </div>
                    <div>
                        <label for="Type" class="form-label">Expiration date</label>
                        <input type="date" class="form-control" :value = "GetValidDate()" id="Type" placeholder="" disabled>
                    </div>

                    <div>
                        <label for="Type" class="form-label">PromoCode</label>
                        <input type="text" class="form-control" :value = "promoCodeString" id="Type" placeholder="">
                        <button class="w-100 btn btn-lg btn-dark" v-on:click = "CheckPromo">Check promocode</button>
                    </div>
                    <button class="w-100 btn btn-lg btn-dark" style="margin-top: 50px;" v-on:click = "CreateMembership">Create</button>
                </div>
            </div>
        </div>
    </div>
    	`,
    mounted() {
        axios
            .get('rest/membershipType/')
            .then(response => (this.membershipTypes = response.data))
        axios
            .get('rest/login/loginstat')
            .then(response => {
                this.user = response.data;
            })
    },
    methods: {

        GetValidDate: function () {
            var today = new Date()
            var selected = this.GetSelection()
            var type = selected.type
            if (type === "Yearly") {
                today.setFullYear(today.getFullYear() + 1)
            }
            else if (type === "Monthly") {
                today.setMonth(today.getMonth() + 1)
            }
            else {
                today.setDate(today.getDate() + 7)
            }
            return today.toISOString().slice(0, 10)
        },

        GetSelection: function () {
            return this.membershipTypes.find(item => item.id === this.selectedMembershipType)
        },

        CheckPromo: function () {
            this.promoCodePercentage = 0.3
        },

        CreateMembership: function () {
            try {
                this.membership.paymentDate = new Date().getDate()
                this.membership.validUntil = this.GetValidDate()
                this.membership.type = this.GetSelection()
                this.membership.buyer = this.user
                axios.post('rest/membership/', this.membership)
                .catch((e) => {alert(e?.response?.data)})
                router.push(`/`)
            } catch (error) {
                alert(error)
            }

        }

    },
    computed: {
        GetPrice() {
            var startingPrice = this.GetSelection().price
            var userTypeDiscount = this.user.type.discount
            return startingPrice * (1 - userTypeDiscount) * (1 - this.promoCodePercentage)
        }
    }
});