import { Component, OnInit } from '@angular/core';
import {AccountService} from "../service/account.service";

@Component({
  selector: 'app-table-list',
  templateUrl: './table-list.component.html',
  styleUrls: ['./table-list.component.css']
})
export class TableListComponent implements OnInit {

    account: Account;

  constructor(public accountService: AccountService) { }

  ngOnInit() {
      this.accountService.getAccountByUserId(1).then( account => {
          this.account = account;
      })
  }

}
