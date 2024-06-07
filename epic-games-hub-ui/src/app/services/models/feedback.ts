/* tslint:disable */
/* eslint-disable */
import { Game } from '../models/game';
export interface Feedback {
  comment?: string;
  createdBy?: number;
  createdDate?: string;
  game?: Game;
  id?: number;
  lastModifiedBy?: number;
  lastModifiedDate?: string;
  note?: number;
}
