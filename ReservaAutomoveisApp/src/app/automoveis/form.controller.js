export default class FormController {

    constructor($state, $stateParams, MarcaServico, AutomovelServico, Notification) {
        this._state = $state;
        this._stateParam = $stateParams;
        this._notify = Notification;
        this.title = 'Adicionando registro';

        this._servicoMarca = MarcaServico;
        this.marcas = []
        MarcaServico.findAll().then(data => {
            console.log(data)
            this.marcas = data;
        })

        this._servico = AutomovelServico;
        this.record = {};
        this.load();

    }

    loadMarcas() {
        this._servicoMarca.findAll()
            .then(data => {
                this.marcas = data;
            })
            .catch(error => {
                console.log(error)
            })
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
                this._state.go('automovel.list')
            }).catch(erro => {
                console.log("tsteErro: " + erro.ConstraintViolationImpl)
                console.log(erro)
                this._notify.error('Erro ao salvar o registro!' + "<br><br> " + erro.data.rootMessage)
            })
    }

    reservar() {
        this._servico.save(this.record)
        .then(resp => {
            this._notify.success('Registro salvo com sucesso!')
            this._state.go('automovel.list')
        }).catch(erro => {
            console.log("tsteErro: " + erro.ConstraintViolationImpl)
            console.log(erro)
            this._notify.error('Erro ao salvar o registro!' + "<br><br> " + erro.data.rootMessage)
        })

    }

}

FormController.$inject = ['$state', '$stateParams', 'MarcaServico', 'AutomovelServico', 'Notification']
