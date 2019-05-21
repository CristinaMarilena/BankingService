import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

export interface UserAccountsInterface{
  username: string,
  accounts: Array<AccountInterface>
}

export interface AccountInterface{
  id: number,
  accountNumber: number,
  accountBalance: number, 
  isPrimary: boolean,
  type: string
}

let basicUrl = "http://localhost:8080/";
let usersBasicUrl = "users/";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  userAccounts: UserAccountsInterface;

  constructor(private httpClient: HttpClient) {}

  get(){
    console.log("in the service");
    return this.httpClient.get<UserAccountsInterface>(basicUrl + usersBasicUrl + "accounts/1")
    .subscribe((data: UserAccountsInterface) => {
      this.userAccounts = data;
      console.log(data);
    });
  }
}
