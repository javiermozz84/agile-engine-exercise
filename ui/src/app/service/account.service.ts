import { Injectable } from '@angular/core';
import {environment} from "../../environments/environment";
import {HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AccountService {

    API_HOST = environment.apiRestEndpoint;
    ACCOUNT_ENDPOINT = '/accounting/api/';

    constructor(public httpClient: HttpClient) { }


    getAccountByUserId(userId: number): Promise<Account> {
        return this.httpClient.get<Account>(this.API_HOST + this.ACCOUNT_ENDPOINT + `user/${userId}/account`).toPromise();
    }
}
