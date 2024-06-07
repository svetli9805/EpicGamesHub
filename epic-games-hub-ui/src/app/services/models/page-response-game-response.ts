/* tslint:disable */
/* eslint-disable */
import { GameResponse } from '../models/game-response';
export interface PageResponseGameResponse {
  content?: Array<GameResponse>;
  first?: boolean;
  last?: boolean;
  number?: number;
  size?: number;
  totalElements?: number;
  totalPages?: number;
}
