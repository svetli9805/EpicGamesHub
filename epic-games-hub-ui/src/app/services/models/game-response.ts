/* tslint:disable */
/* eslint-disable */
import { Feedback } from '../models/feedback';
import { GameTransactionHistory } from '../models/game-transaction-history';
export interface GameResponse {
  availability?: boolean;
  coverImage?: Array<string>;
  description?: string;
  feedbacks?: Array<Feedback>;
  genre?: string;
  histories?: Array<GameTransactionHistory>;
  id?: number;
  owner?: string;
  platform?: string;
  publisher?: string;
  rating?: number;
  releaseDate?: string;
  sharable?: boolean;
  title?: string;
}
