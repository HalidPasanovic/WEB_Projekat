Vue.component("trainings", {
    data: function () {
        return {
            user: null,
            trainings: null,
            searchQuery: null,
            price1: null,
            price2: null,
            date1: null,
            date2: null,
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
                            <option value = "" disabled selected>Training type</option>
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
                                <th scope="col">Name</th>
                                <th scope="col">Sport facility</th>
                                <th scope="col">Date</th>
                                <th scope="col"></th>
                            </tr>
                        </thead>
                        <tbody>
                        <tr v-for="(f, index) in resultQuery">
                            <td>{{f?.training?.name}}</td>
                            <td>{{f?.training?.facility?.name}}</td>
                            <td>{{f?.applicationDateTime}}</td>
                            <td><button v-if="CheckIfCanDelete(f)" class="btn btn-dark" v-on:click = "DeleteHistory(f?.id, index)">Delete</button></td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div style="margin-top: 20px; margin-bottom: 20px;">
                    <button class="w-100 btn btn-lg btn-dark" v-on:click = "Personal">Personal trainings</button>
                    <button class="w-100 btn btn-lg btn-dark" v-on:click = "Group">Group trainings</button>
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
                    .get('rest/trainingHistory/specific/trainer/' + this.user.id)
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

        DeleteHistory: function (id, index) {
            r = confirm("Are you sure?")
            if (r) {
                axios
                    .delete('rest/trainingHistory/' + id)
                    .then(response => (this.trainings.splice(index, 1)))
            }
        },

        CheckIfCanDelete: function (time) {
            var selectedTime = Date.parse(time.applicationDateTime)
            var today = new Date()
            today.setDate(today.getDate() + 2)
            return selectedTime > today && time.training.type.id == -1220821779
        },

        Personal: function () {
            router.push(`/personalTrainings`)
        },

        Group: function () {
            router.push(`/groupTrainings`)
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

        filterDate: function (item) {
            if ((this.date1 === null || this.date1 === '') || (this.date2 === null || this.date2 === '')) {
                return true
            }
            var value = this.findProp(item, 'applicationDateTime')
            return value >= this.date1 && value <= this.date2
        },

        filterPrice: function (item) {
            if ((this.price1 === null || this.price1 === '') || (this.price2 === null || this.price2 === '')) {
                return true
            }
            var value = this.findProp(item, 'training.aditionalCost')
            return value >= this.price1 && value <= this.price2
        },

        filterName: function (item, v) {
            var value = this.findProp(item, 'training.name')
            return value.toLowerCase().includes(v)
        }
    },
    computed: {
        resultQuery() {
            var result = this.trainings
            if (result) {
                result = result.sort((a, b) => {
                    let modifier = 1;
                    if (this.currentSortDir === 'desc') modifier = -1;
                    if (this.findProp(a, this.currentSort) < this.findProp(b, this.currentSort)) return -1 * modifier;
                    if (this.findProp(a, this.currentSort) > this.findProp(b, this.currentSort)) return 1 * modifier;
                    return 0;
                })
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
                        return this.searchQuery.toLowerCase().split(' ').every(v => this.filterName(item, v))
                    })
                }
                result = result.filter((item) => {
                    return this.filterPrice(item)
                })
                result = result.filter((item) => {
                    return this.filterDate(item)
                })
            }
            return result
        }
    }
});