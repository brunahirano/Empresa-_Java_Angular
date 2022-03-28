'use strict';

customElements.define('compodoc-menu', class extends HTMLElement {
    constructor() {
        super();
        this.isNormalMode = this.getAttribute('mode') === 'normal';
    }

    connectedCallback() {
        this.render(this.isNormalMode);
    }

    render(isNormalMode) {
        let tp = lithtml.html(`
        <nav>
            <ul class="list">
                <li class="title">
                    <a href="index.html" data-type="index-link">empresa-front documentation</a>
                </li>

                <li class="divider"></li>
                ${ isNormalMode ? `<div id="book-search-input" role="search"><input type="text" placeholder="Type to search"></div>` : '' }
                <li class="chapter">
                    <a data-type="chapter-link" href="index.html"><span class="icon ion-ios-home"></span>Getting started</a>
                    <ul class="links">
                        <li class="link">
                            <a href="overview.html" data-type="chapter-link">
                                <span class="icon ion-ios-keypad"></span>Overview
                            </a>
                        </li>
                        <li class="link">
                            <a href="index.html" data-type="chapter-link">
                                <span class="icon ion-ios-paper"></span>README
                            </a>
                        </li>
                                <li class="link">
                                    <a href="dependencies.html" data-type="chapter-link">
                                        <span class="icon ion-ios-list"></span>Dependencies
                                    </a>
                                </li>
                                <li class="link">
                                    <a href="properties.html" data-type="chapter-link">
                                        <span class="icon ion-ios-apps"></span>Properties
                                    </a>
                                </li>
                    </ul>
                </li>
                    <li class="chapter modules">
                        <a data-type="chapter-link" href="modules.html">
                            <div class="menu-toggler linked" data-toggle="collapse" ${ isNormalMode ?
                                'data-target="#modules-links"' : 'data-target="#xs-modules-links"' }>
                                <span class="icon ion-ios-archive"></span>
                                <span class="link-name">Modules</span>
                                <span class="icon ion-ios-arrow-down"></span>
                            </div>
                        </a>
                        <ul class="links collapse " ${ isNormalMode ? 'id="modules-links"' : 'id="xs-modules-links"' }>
                            <li class="link">
                                <a href="modules/AppModule.html" data-type="entity-link" >AppModule</a>
                                    <li class="chapter inner">
                                        <div class="simple menu-toggler" data-toggle="collapse" ${ isNormalMode ?
                                            'data-target="#components-links-module-AppModule-e9f5dce9856980818fae2d7e8fb13c526a34eca989e11235dd2b1f0568684b592e830542ccc5a7ab161b8067a5070471c94d6ece81016203c1774b6e0d9ee15f"' : 'data-target="#xs-components-links-module-AppModule-e9f5dce9856980818fae2d7e8fb13c526a34eca989e11235dd2b1f0568684b592e830542ccc5a7ab161b8067a5070471c94d6ece81016203c1774b6e0d9ee15f"' }>
                                            <span class="icon ion-md-cog"></span>
                                            <span>Components</span>
                                            <span class="icon ion-ios-arrow-down"></span>
                                        </div>
                                        <ul class="links collapse" ${ isNormalMode ? 'id="components-links-module-AppModule-e9f5dce9856980818fae2d7e8fb13c526a34eca989e11235dd2b1f0568684b592e830542ccc5a7ab161b8067a5070471c94d6ece81016203c1774b6e0d9ee15f"' :
                                            'id="xs-components-links-module-AppModule-e9f5dce9856980818fae2d7e8fb13c526a34eca989e11235dd2b1f0568684b592e830542ccc5a7ab161b8067a5070471c94d6ece81016203c1774b6e0d9ee15f"' }>
                                            <li class="link">
                                                <a href="components/AppComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >AppComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/AtribuirCargoAoMentorComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >AtribuirCargoAoMentorComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/AtribuirCargoComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >AtribuirCargoComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/CadastrarBoletoComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >CadastrarBoletoComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/CadastrarMentorComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >CadastrarMentorComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/CadastroCargoComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >CadastroCargoComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/CadastroFuncComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >CadastroFuncComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/EdicaoBoletoComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >EdicaoBoletoComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/EdicaoCargoComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >EdicaoCargoComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/EdicaoFuncComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >EdicaoFuncComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/EdicaoMentorComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >EdicaoMentorComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/ExclusaoBoletoComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >ExclusaoBoletoComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/ExclusaoCargoComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >ExclusaoCargoComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/ExclusaoFuncComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >ExclusaoFuncComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/ExclusaoMentorComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >ExclusaoMentorComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/HeaderComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >HeaderComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/HomeComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >HomeComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/ListaBoletosFuncComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >ListaBoletosFuncComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/ListaCargoComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >ListaCargoComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/ListaFuncComCargoComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >ListaFuncComCargoComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/ListaFuncDoCargoComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >ListaFuncDoCargoComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/ListaMentorComCargoComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >ListaMentorComCargoComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/LoginComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >LoginComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/MentorDoCargoComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >MentorDoCargoComponent</a>
                                            </li>
                                        </ul>
                                    </li>
                            </li>
                            <li class="link">
                                <a href="modules/AppRoutingModule.html" data-type="entity-link" >AppRoutingModule</a>
                            </li>
                </ul>
                </li>
                        <li class="chapter">
                            <div class="simple menu-toggler" data-toggle="collapse" ${ isNormalMode ? 'data-target="#injectables-links"' :
                                'data-target="#xs-injectables-links"' }>
                                <span class="icon ion-md-arrow-round-down"></span>
                                <span>Injectables</span>
                                <span class="icon ion-ios-arrow-down"></span>
                            </div>
                            <ul class="links collapse " ${ isNormalMode ? 'id="injectables-links"' : 'id="xs-injectables-links"' }>
                                <li class="link">
                                    <a href="injectables/BoletoMedicoService.html" data-type="entity-link" >BoletoMedicoService</a>
                                </li>
                                <li class="link">
                                    <a href="injectables/CargoService.html" data-type="entity-link" >CargoService</a>
                                </li>
                                <li class="link">
                                    <a href="injectables/FuncionarioService.html" data-type="entity-link" >FuncionarioService</a>
                                </li>
                                <li class="link">
                                    <a href="injectables/MentorService.html" data-type="entity-link" >MentorService</a>
                                </li>
                            </ul>
                        </li>
                    <li class="chapter">
                        <div class="simple menu-toggler" data-toggle="collapse" ${ isNormalMode ? 'data-target="#interfaces-links"' :
                            'data-target="#xs-interfaces-links"' }>
                            <span class="icon ion-md-information-circle-outline"></span>
                            <span>Interfaces</span>
                            <span class="icon ion-ios-arrow-down"></span>
                        </div>
                        <ul class="links collapse " ${ isNormalMode ? ' id="interfaces-links"' : 'id="xs-interfaces-links"' }>
                            <li class="link">
                                <a href="interfaces/Boleto.html" data-type="entity-link" >Boleto</a>
                            </li>
                            <li class="link">
                                <a href="interfaces/Cargo.html" data-type="entity-link" >Cargo</a>
                            </li>
                            <li class="link">
                                <a href="interfaces/Funcionario.html" data-type="entity-link" >Funcionario</a>
                            </li>
                            <li class="link">
                                <a href="interfaces/Mentor.html" data-type="entity-link" >Mentor</a>
                            </li>
                        </ul>
                    </li>
                    <li class="chapter">
                        <div class="simple menu-toggler" data-toggle="collapse" ${ isNormalMode ? 'data-target="#miscellaneous-links"'
                            : 'data-target="#xs-miscellaneous-links"' }>
                            <span class="icon ion-ios-cube"></span>
                            <span>Miscellaneous</span>
                            <span class="icon ion-ios-arrow-down"></span>
                        </div>
                        <ul class="links collapse " ${ isNormalMode ? 'id="miscellaneous-links"' : 'id="xs-miscellaneous-links"' }>
                            <li class="link">
                                <a href="miscellaneous/variables.html" data-type="entity-link">Variables</a>
                            </li>
                        </ul>
                    </li>
                        <li class="chapter">
                            <a data-type="chapter-link" href="routes.html"><span class="icon ion-ios-git-branch"></span>Routes</a>
                        </li>
                    <li class="chapter">
                        <a data-type="chapter-link" href="coverage.html"><span class="icon ion-ios-stats"></span>Documentation coverage</a>
                    </li>
                    <li class="divider"></li>
                    <li class="copyright">
                        Documentation generated using <a href="https://compodoc.app/" target="_blank">
                            <img data-src="images/compodoc-vectorise.png" class="img-responsive" data-type="compodoc-logo">
                        </a>
                    </li>
            </ul>
        </nav>
        `);
        this.innerHTML = tp.strings;
    }
});