import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IProvinceMySuffix, defaultValue } from 'app/shared/model/province-my-suffix.model';

export const ACTION_TYPES = {
  FETCH_PROVINCE_LIST: 'province/FETCH_PROVINCE_LIST',
  FETCH_PROVINCE: 'province/FETCH_PROVINCE',
  CREATE_PROVINCE: 'province/CREATE_PROVINCE',
  UPDATE_PROVINCE: 'province/UPDATE_PROVINCE',
  DELETE_PROVINCE: 'province/DELETE_PROVINCE',
  RESET: 'province/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IProvinceMySuffix>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type ProvinceMySuffixState = Readonly<typeof initialState>;

// Reducer

export default (state: ProvinceMySuffixState = initialState, action): ProvinceMySuffixState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_PROVINCE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_PROVINCE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_PROVINCE):
    case REQUEST(ACTION_TYPES.UPDATE_PROVINCE):
    case REQUEST(ACTION_TYPES.DELETE_PROVINCE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_PROVINCE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_PROVINCE):
    case FAILURE(ACTION_TYPES.CREATE_PROVINCE):
    case FAILURE(ACTION_TYPES.UPDATE_PROVINCE):
    case FAILURE(ACTION_TYPES.DELETE_PROVINCE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_PROVINCE_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_PROVINCE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_PROVINCE):
    case SUCCESS(ACTION_TYPES.UPDATE_PROVINCE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_PROVINCE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {}
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState
      };
    default:
      return state;
  }
};

const apiUrl = 'api/provinces';

// Actions

export const getEntities: ICrudGetAllAction<IProvinceMySuffix> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_PROVINCE_LIST,
  payload: axios.get<IProvinceMySuffix>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IProvinceMySuffix> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_PROVINCE,
    payload: axios.get<IProvinceMySuffix>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IProvinceMySuffix> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_PROVINCE,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IProvinceMySuffix> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_PROVINCE,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IProvinceMySuffix> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_PROVINCE,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
