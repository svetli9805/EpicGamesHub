/* tslint:disable */
/* eslint-disable */
import { Game } from '../models/game';
import { User } from '../models/user';
export interface GameTransactionHistory {
  createdBy?: number;
  createdDate?: string;
  game?: Game;
  id?: number;
  lastModifiedBy?: number;
  lastModifiedDate?: string;
  returnApproved?: boolean;
  returned?: boolean;
  user?: User;
}
