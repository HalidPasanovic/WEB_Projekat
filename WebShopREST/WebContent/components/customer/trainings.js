Vue.component("trainings", {
    data: function () {
        return {
            user: null,
            trainings: null,
            searchQuery: null,
            price1: null,
            price2: null,
            date1: null,
            date2: '9999-12-31',
            currentSort: 'status',
            currentSortDir: 'desc',
            selectedType: '',
            selectedTrainingType: '',
            selectedSearch: '',
            facilityTypes: null,
            trainingTypes: null,
        }
    },
    template: ` 
		<div class="d-flex flex-nowrap">
            <div style="width: 100%;">
                <div style="display: flex; justify-content: center; margin-top: 20px; margin-bottom: 20px;">
                    <h3>All trainings</h3>
                </div>
                 <div style="float: right;" class="d-flex flex-nowrap">
                    <div style="width: 150px;">
                        <select v-model = "selectedType" class="form-control">
                            <option value = "" disabled selected>Facility type</option>
                            <option value = "All" >All</option>
                            <option v-for="(f, index) in facilityTypes" :value = "f.name">{{f.name}}</option>
                        </select>
                    </div>
                    <div style="width: 150px;">
                        <select v-model = "selectedTrainingType" class="form-control">
                            <option value = "" disabled selected>Facility type</option>
                            <option value = "All" >All</option>
                            <option v-for="(f, index) in trainingTypes" :value = "f.name">{{f.name}}</option>
                        </select>
                    </div>
                    <div class="search-container">
                        <input type="text" class="form-control btn-dark" v-model="searchQuery" placeholder="Name" name="search">
                        <div class="d-flex flex-nowrap">
                            <input type="date" class="form-control btn-dark" v-model="date1" placeholder="From" name="search">
                            <input type="date" class="form-control btn-dark" v-model="date2" placeholder="To" name="search">
                        </div>
                        <div class="d-flex flex-nowrap">
                            <input type="number" class="form-control btn-dark" v-model="price1" placeholder="From" name="search">
                            <input type="number" class="form-control btn-dark" v-model="price2" placeholder="To" name="search">
                        </div>
                    </div>
			    </div>
                <div>
                    <table class="table table-striped table-hover table-dark">
                        <thead>
                            <tr>
                                <th @click="sort('training.name')" scope="col">Name</th>
                                <th @click="sort('training.facility.name')"  scope="col">Sport facility</th>
                                <th @click="sort('applicationDateTime')" scope="col">Date</th>
                                <th scope="col"></th>
                            </tr>
                        </thead>
                        <tbody>
                        <tr v-for="(f, index) in resultQuery">
                            <td>{{f?.training?.name}}</td>
                            <td>{{f?.training?.facility?.name}}</td>
                            <td>{{f?.applicationDateTime}}</td>
                            <td><button v-if="CheckIfCanDelete(f?.applicationDateTime)" class="btn btn-dark" v-on:click = "DeleteHistory(f?.id, index)">Delete</button></td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div style="margin-top: 20px; margin-bottom: 20px;">
                    <button class="w-100 btn btn-lg btn-dark" v-on:click = "CreateMembership">Create membership</button>
                </div>
            </div>
        </div>
    	`,
    mounted() {
        axios
            .get('rest/login/loginstat')
            .then(response => {
                this.user = response.data;
                axios
                    .get('rest/trainingHistory/specific/' + this.user.id)
                    .then(response => (this.trainings = response.data))
                axios
                    .get('rest/facilitytypes/')
                    .then(response => {
                        this.facilityTypes = response.data
                    })
                axios
                    .get('rest/trainingType/')
                    .then(response => {
                        this.trainingTypes = response.data
                    })
            })
    },
    methods: {

        CreateMembership: function () {
            router.push(`/membership/`)
        },

        DeleteHistory: function (id, index) {
            r = confirm("Are you sure?")
            if (r) {
                axios
                    .delete('rest/trainingHistory/' + id)
                    .then(response => (this.trainings.splice(index, 1)))
            }
        },

        CheckIfCanDelete: function (time) {
            var selectedTime = Date.parse(time)
            var today = new Date()
            today.setDate(today.getDate() + 2)
            return selectedTime > today
        },

        sort: function (s) {
            //if s == current sort, reverse
            if (s === this.currentSort) {
                this.currentSortDir = this.currentSortDir === 'asc' ? 'desc' : 'asc';
            }
            this.currentSort = s;
        },

        findProp: function (obj, prop, defval) {
            if (typeof defval == 'undefined') defval = null;
            prop = prop.split('.');
            for (var i = 0; i < prop.length; i++) {
                if (typeof obj[prop[i]] == 'undefined')
                    return defval;
                obj = obj[prop[i]];
            }
            return obj;
        },

        filter: function (item, v) {
            if (this.selectedSearch == '') {
                return true
            } else if (this.selectedSearch == 'date') {
                var value = this.findProp(item, 'applicationDateTime')
                return value > this.date1 && value < this.date2
            } else if (this.selectedSearch == 'price') {
                var value = this.findProp(item, 'training.aditionalCost')
                return value > this.price1 && value < this.price2
            } else {
                var value = this.findProp(item, this.selectedSearch)
                return value.toLowerCase().includes(v)
            }
        }
    },
    computed: {
        resultQuery() {
            if (this.trainings) {
                this.trainings = this.trainings.sort((a, b) => {
                    let modifier = 1;
                    if (this.currentSortDir === 'desc') modifier = -1;
                    if (this.findProp(a, this.currentSort) < this.findProp(b, this.currentSort)) return -1 * modifier;
                    if (this.findProp(a, this.currentSort) > this.findProp(b, this.currentSort)) return 1 * modifier;
                    return 0;
                })
            }
            var result = this.trainings
            if (this.selectedType != 'All' && this.selectedType != '') {
                result = result.filter((item) => {
                    return item.training.facility.type.name == this.selectedType
                })
            }
            if (this.selectedTrainingType != 'All' && this.selectedTrainingType != '') {
                result = result.filter((item) => {
                    return item.training.type.name == this.selectedTrainingType
                })
            }
            if (this.searchQuery) {
                result = result.filter((item) => {
                    return this.searchQuery.toLowerCase().split(' ').every(v => this.filter(item, v))
                })
            }
            return result
        }
    }
});