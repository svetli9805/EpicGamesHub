/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { PageResponseBorrowedGameResponse } from '../../models/page-response-borrowed-game-response';

export interface FindAllReturnedGames$Params {
  page?: number;
  size?: number;
}

export function findAllReturnedGames(http: HttpClient, rootUrl: string, params?: FindAllReturnedGames$Params, context?: HttpContext): Observable<StrictHttpResponse<PageResponseBorrowedGameResponse>> {
  const rb = new RequestBuilder(rootUrl, findAllReturnedGames.PATH, 'get');
  if (params) {
    rb.query('page', params.page, {});
    rb.query('size', params.size, {});
  }

  return http.request(
    rb.build({ responseType: 'json', accept: 'application/json', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<PageResponseBorrowedGameResponse>;
    })
  );
}

findAllReturnedGames.PATH = '/games/returned';