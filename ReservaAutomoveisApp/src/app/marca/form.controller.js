export default class FormController {

    constructor($state, $stateParams, MarcaServico, Notification) {
        this._state = $state;
        this._stateParam = $stateParams;
        this._servico = MarcaServico;
        this._notify = Notification;
        this.title = 'Adicionando registro';
        this.record = {};
        this.load();
    }

    load() {
        if (this._stateParam.id) {
            this.title = 'Editando registro'
            this._servico.findById(this._stateParam.id)
                .then(data => {
                    this.record = data
                })
        }
    }

    save() {
        this._servico.save(this.record)
            .then(resp => {
                this._notify.success('Registro salvo com sucesso!')
                this._state.go('marca.list')
            }).catch(erro => {
                console.log(erro)
                this._notify.error('Erro ao salvar o registro!' + "<br><br> " + erro.data.rootMessage)
            })
    }

}

FormController.$inject = ['$state', '$stateParams', 'MarcaServico', 'Notification']
