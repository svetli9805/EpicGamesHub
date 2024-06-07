/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { BaseService } from '../base-service';
import { ApiConfiguration } from '../api-configuration';
import { StrictHttpResponse } from '../strict-http-response';

import { approveReturnBorrowGame } from '../fn/game/approve-return-borrow-game';
import { ApproveReturnBorrowGame$Params } from '../fn/game/approve-return-borrow-game';
import { borrowGame } from '../fn/game/borrow-game';
import { BorrowGame$Params } from '../fn/game/borrow-game';
import { findAllBorrowedBooks } from '../fn/game/find-all-borrowed-books';
import { FindAllBorrowedBooks$Params } from '../fn/game/find-all-borrowed-books';
import { findAllGames } from '../fn/game/find-all-games';
import { FindAllGames$Params } from '../fn/game/find-all-games';
import { findAllGamesByOwner } from '../fn/game/find-all-games-by-owner';
import { FindAllGamesByOwner$Params } from '../fn/game/find-all-games-by-owner';
import { findAllReturnedGames } from '../fn/game/find-all-returned-games';
import { FindAllReturnedGames$Params } from '../fn/game/find-all-returned-games';
import { findGameById } from '../fn/game/find-game-by-id';
import { FindGameById$Params } from '../fn/game/find-game-by-id';
import { GameResponse } from '../models/game-response';
import { PageResponseBorrowedGameResponse } from '../models/page-response-borrowed-game-response';
import { PageResponseGameResponse } from '../models/page-response-game-response';
import { returnBorrowGame } from '../fn/game/return-borrow-game';
import { ReturnBorrowGame$Params } from '../fn/game/return-borrow-game';
import { saveGame } from '../fn/game/save-game';
import { SaveGame$Params } from '../fn/game/save-game';
import { updateShareableStatus } from '../fn/game/update-shareable-status';
import { UpdateShareableStatus$Params } from '../fn/game/update-shareable-status';
import { uploadGameImageCover } from '../fn/game/upload-game-image-cover';
import { UploadGameImageCover$Params } from '../fn/game/upload-game-image-cover';

@Injectable({ providedIn: 'root' })
export class GameService extends BaseService {
  constructor(config: ApiConfiguration, http: HttpClient) {
    super(config, http);
  }

  /** Path part for operation `findAllGames()` */
  static readonly FindAllGamesPath = '/games';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `findAllGames()` instead.
   *
   * This method doesn't expect any request body.
   */
  findAllGames$Response(params?: FindAllGames$Params, context?: HttpContext): Observable<StrictHttpResponse<PageResponseGameResponse>> {
    return findAllGames(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `findAllGames$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  findAllGames(params?: FindAllGames$Params, context?: HttpContext): Observable<PageResponseGameResponse> {
    return this.findAllGames$Response(params, context).pipe(
      map((r: StrictHttpResponse<PageResponseGameResponse>): PageResponseGameResponse => r.body)
    );
  }

  /** Path part for operation `saveGame()` */
  static readonly SaveGamePath = '/games';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `saveGame()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  saveGame$Response(params: SaveGame$Params, context?: HttpContext): Observable<StrictHttpResponse<GameResponse>> {
    return saveGame(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `saveGame$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  saveGame(params: SaveGame$Params, context?: HttpContext): Observable<GameResponse> {
    return this.saveGame$Response(params, context).pipe(
      map((r: StrictHttpResponse<GameResponse>): GameResponse => r.body)
    );
  }

  /** Path part for operation `uploadGameImageCover()` */
  static readonly UploadGameImageCoverPath = '/games/cover/{game-id}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `uploadGameImageCover()` instead.
   *
   * This method sends `multipart/form-data` and handles request body of type `multipart/form-data`.
   */
  uploadGameImageCover$Response(params: UploadGameImageCover$Params, context?: HttpContext): Observable<StrictHttpResponse<{
}>> {
    return uploadGameImageCover(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `uploadGameImageCover$Response()` instead.
   *
   * This method sends `multipart/form-data` and handles request body of type `multipart/form-data`.
   */
  uploadGameImageCover(params: UploadGameImageCover$Params, context?: HttpContext): Observable<{
}> {
    return this.uploadGameImageCover$Response(params, context).pipe(
      map((r: StrictHttpResponse<{
}>): {
} => r.body)
    );
  }

  /** Path part for operation `borrowGame()` */
  static readonly BorrowGamePath = '/games/borrow/{game-id}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `borrowGame()` instead.
   *
   * This method doesn't expect any request body.
   */
  borrowGame$Response(params: BorrowGame$Params, context?: HttpContext): Observable<StrictHttpResponse<number>> {
    return borrowGame(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `borrowGame$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  borrowGame(params: BorrowGame$Params, context?: HttpContext): Observable<number> {
    return this.borrowGame$Response(params, context).pipe(
      map((r: StrictHttpResponse<number>): number => r.body)
    );
  }

  /** Path part for operation `updateShareableStatus()` */
  static readonly UpdateShareableStatusPath = '/games/shareable/{game-id}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `updateShareableStatus()` instead.
   *
   * This method doesn't expect any request body.
   */
  updateShareableStatus$Response(params: UpdateShareableStatus$Params, context?: HttpContext): Observable<StrictHttpResponse<number>> {
    return updateShareableStatus(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `updateShareableStatus$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  updateShareableStatus(params: UpdateShareableStatus$Params, context?: HttpContext): Observable<number> {
    return this.updateShareableStatus$Response(params, context).pipe(
      map((r: StrictHttpResponse<number>): number => r.body)
    );
  }

  /** Path part for operation `returnBorrowGame()` */
  static readonly ReturnBorrowGamePath = '/games/borrow/return/{game-id}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `returnBorrowGame()` instead.
   *
   * This method doesn't expect any request body.
   */
  returnBorrowGame$Response(params: ReturnBorrowGame$Params, context?: HttpContext): Observable<StrictHttpResponse<number>> {
    return returnBorrowGame(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `returnBorrowGame$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  returnBorrowGame(params: ReturnBorrowGame$Params, context?: HttpContext): Observable<number> {
    return this.returnBorrowGame$Response(params, context).pipe(
      map((r: StrictHttpResponse<number>): number => r.body)
    );
  }

  /** Path part for operation `approveReturnBorrowGame()` */
  static readonly ApproveReturnBorrowGamePath = '/games/borrow/return/approve/{game-id}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `approveReturnBorrowGame()` instead.
   *
   * This method doesn't expect any request body.
   */
  approveReturnBorrowGame$Response(params: ApproveReturnBorrowGame$Params, context?: HttpContext): Observable<StrictHttpResponse<number>> {
    return approveReturnBorrowGame(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `approveReturnBorrowGame$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  approveReturnBorrowGame(params: ApproveReturnBorrowGame$Params, context?: HttpContext): Observable<number> {
    return this.approveReturnBorrowGame$Response(params, context).pipe(
      map((r: StrictHttpResponse<number>): number => r.body)
    );
  }

  /** Path part for operation `findGameById()` */
  static readonly FindGameByIdPath = '/games/{game-id}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `findGameById()` instead.
   *
   * This method doesn't expect any request body.
   */
  findGameById$Response(params: FindGameById$Params, context?: HttpContext): Observable<StrictHttpResponse<GameResponse>> {
    return findGameById(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `findGameById$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  findGameById(params: FindGameById$Params, context?: HttpContext): Observable<GameResponse> {
    return this.findGameById$Response(params, context).pipe(
      map((r: StrictHttpResponse<GameResponse>): GameResponse => r.body)
    );
  }

  /** Path part for operation `findAllReturnedGames()` */
  static readonly FindAllReturnedGamesPath = '/games/returned';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `findAllReturnedGames()` instead.
   *
   * This method doesn't expect any request body.
   */
  findAllReturnedGames$Response(params?: FindAllReturnedGames$Params, context?: HttpContext): Observable<StrictHttpResponse<PageResponseBorrowedGameResponse>> {
    return findAllReturnedGames(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `findAllReturnedGames$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  findAllReturnedGames(params?: FindAllReturnedGames$Params, context?: HttpContext): Observable<PageResponseBorrowedGameResponse> {
    return this.findAllReturnedGames$Response(params, context).pipe(
      map((r: StrictHttpResponse<PageResponseBorrowedGameResponse>): PageResponseBorrowedGameResponse => r.body)
    );
  }

  /** Path part for operation `findAllGamesByOwner()` */
  static readonly FindAllGamesByOwnerPath = '/games/owner';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `findAllGamesByOwner()` instead.
   *
   * This method doesn't expect any request body.
   */
  findAllGamesByOwner$Response(params?: FindAllGamesByOwner$Params, context?: HttpContext): Observable<StrictHttpResponse<PageResponseGameResponse>> {
    return findAllGamesByOwner(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `findAllGamesByOwner$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  findAllGamesByOwner(params?: FindAllGamesByOwner$Params, context?: HttpContext): Observable<PageResponseGameResponse> {
    return this.findAllGamesByOwner$Response(params, context).pipe(
      map((r: StrictHttpResponse<PageResponseGameResponse>): PageResponseGameResponse => r.body)
    );
  }

  /** Path part for operation `findAllBorrowedBooks()` */
  static readonly FindAllBorrowedBooksPath = '/games/borrowed';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `findAllBorrowedBooks()` instead.
   *
   * This method doesn't expect any request body.
   */
  findAllBorrowedBooks$Response(params?: FindAllBorrowedBooks$Params, context?: HttpContext): Observable<StrictHttpResponse<PageResponseBorrowedGameResponse>> {
    return findAllBorrowedBooks(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `findAllBorrowedBooks$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  findAllBorrowedBooks(params?: FindAllBorrowedBooks$Params, context?: HttpContext): Observable<PageResponseBorrowedGameResponse> {
    return this.findAllBorrowedBooks$Response(params, context).pipe(
      map((r: StrictHttpResponse<PageResponseBorrowedGameResponse>): PageResponseBorrowedGameResponse => r.body)
    );
  }

}
