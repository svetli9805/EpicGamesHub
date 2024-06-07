/* tslint:disable */
/* eslint-disable */
import { Feedback } from '../models/feedback';
import { GameTransactionHistory } from '../models/game-transaction-history';
import { User } from '../models/user';
export interface Game {
  availability?: boolean;
  coverImage?: string;
  createdBy?: number;
  createdDate?: string;
  description?: string;
  feedbacks?: Array<Feedback>;
  genre?: string;
  histories?: Array<GameTransactionHistory>;
  id?: number;
  lastModifiedBy?: number;
  lastModifiedDate?: string;
  owner?: User;
  platform?: string;
  publisher?: string;
  rate?: number;
  rating?: number;
  releaseDate?: string;
  shareable?: boolean;
  title?: string;
}
