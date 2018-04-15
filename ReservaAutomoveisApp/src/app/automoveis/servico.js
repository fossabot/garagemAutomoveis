export default class AutomovelServico {

  constructor($http) {
    this._http = $http;
    this._url = 'http://localhost:8080/ReservaAutomoveis-web/api/automoveis';
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
      let url = `${this._url}/${record.id}`;
      console.log(record);
      console.log(url);
      return this._http.put(url, record)
    } else {
      return this._http.post(this._url, record)
    }
  }

  remove(id) {
    return this._http.delete(`${this._url}/${id}`)
  }

}

AutomovelServico.$inject = ['$http']
