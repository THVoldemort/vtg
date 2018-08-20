import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './province-my-suffix.reducer';
import { IProvinceMySuffix } from 'app/shared/model/province-my-suffix.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IProvinceMySuffixProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class ProvinceMySuffix extends React.Component<IProvinceMySuffixProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { provinceList, match } = this.props;
    return (
      <div>
        <h2 id="province-my-suffix-heading">
          <Translate contentKey="vtgApp.province.home.title">Provinces</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp;
            <Translate contentKey="vtgApp.province.home.createLabel">Create new Province</Translate>
          </Link>
        </h2>
        <div className="table-responsive">
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="vtgApp.province.name">Name</Translate>
                </th>
                <th>
                  <Translate contentKey="vtgApp.province.slogan">Slogan</Translate>
                </th>
                <th>
                  <Translate contentKey="vtgApp.province.introduction">Introduction</Translate>
                </th>
                <th>
                  <Translate contentKey="vtgApp.province.filePath1">File Path 1</Translate>
                </th>
                <th>
                  <Translate contentKey="vtgApp.province.filePath2">File Path 2</Translate>
                </th>
                <th>
                  <Translate contentKey="vtgApp.province.filePath3">File Path 3</Translate>
                </th>
                <th>
                  <Translate contentKey="vtgApp.province.filePath4">File Path 4</Translate>
                </th>
                <th>
                  <Translate contentKey="vtgApp.province.filePath5">File Path 5</Translate>
                </th>
                <th>
                  <Translate contentKey="vtgApp.province.commentId">Comment Id</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {provinceList.map((province, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${province.id}`} color="link" size="sm">
                      {province.id}
                    </Button>
                  </td>
                  <td>{province.name}</td>
                  <td>{province.slogan}</td>
                  <td>{province.introduction}</td>
                  <td>{province.filePath1}</td>
                  <td>{province.filePath2}</td>
                  <td>{province.filePath3}</td>
                  <td>{province.filePath4}</td>
                  <td>{province.filePath5}</td>
                  <td>{province.commentId}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${province.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${province.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${province.id}/delete`} color="danger" size="sm">
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        </div>
      </div>
    );
  }
}

const mapStateToProps = ({ province }: IRootState) => ({
  provinceList: province.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(ProvinceMySuffix);
