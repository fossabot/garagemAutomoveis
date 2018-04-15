export default class MarcaServico {

  constructor($http) {
    this._http = $http;
    this._url = 'http://localhost:8080/ReservaAutomoveis-web/api/marcas';
  }

  findAll() {
    return this._http.get(`${this._url}/all`)
      .then(response => response.data)
  }

  findById(id) {
    return this._http.get(`${this._url}/${id}`)
      .then(response => response.data)
  }

  save(record) {
    if (record.id) {
      return this._http.put(`${this._url}/${record.id}`, record)
    } else {      
      return this._http.post(this._url, record)
    }
  }

  remove(id) {
    return this._http.delete(`${this._url}/${id}`)
  }

}

MarcaServico.$inject = ['$http']
