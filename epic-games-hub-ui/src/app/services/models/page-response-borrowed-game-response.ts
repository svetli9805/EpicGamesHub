/* tslint:disable */
/* eslint-disable */
import { BorrowedGameResponse } from '../models/borrowed-game-response';
export interface PageResponseBorrowedGameResponse {
  content?: Array<BorrowedGameResponse>;
  first?: boolean;
  last?: boolean;
  number?: number;
  size?: number;
  totalElements?: number;
  totalPages?: number;
}
